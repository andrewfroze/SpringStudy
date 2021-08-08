package com.andrewfroze.springApp.annotationConfigurated.configuration;

import com.andrewfroze.springApp.annotationConfigurated.App;
import com.andrewfroze.springApp.annotationConfigurated.entity.Client;
import com.andrewfroze.springApp.annotationConfigurated.entity.event.Event;
import com.andrewfroze.springApp.annotationConfigurated.entity.event.EventType;
import com.andrewfroze.springApp.annotationConfigurated.logger.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Value("${client.id}")
    private String id;

    @Value("${client.name}")
    private String name;

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateInstance();
    };

    @Value("src/main/resources/eventLog.log")
    private String filePath;

    @Bean
    public Client client() {
        return new Client(id, name);
    }

    @Bean
    @Scope("prototype")
    public Event event() {
        return new Event(new Date(), dateFormat());
    }

    @Bean("defaultLogger")
    public EventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean
    public EventLogger fileEventLogger() {
        return new FileEventLogger(filePath);
    }

    @Bean
    public EventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger(filePath, 10);
    }

    @Bean
    public EventLogger combinedEventLogger() {
        return new CombinedEventLogger(
                new ArrayList<EventLogger>() {{
                    add(consoleEventLogger());
                    add(fileEventLogger());
                }}
        );
    }

    @Bean
    public Map<EventType, EventLogger> loggers() {
        return new LinkedHashMap<EventType, EventLogger>() {{
            put(EventType.INFO, consoleEventLogger());
            put(EventType.ERROR, combinedEventLogger());
        }};
    }

    @Bean
    public App app() {
        return new App(client(), consoleEventLogger(), loggers());
    }
}
