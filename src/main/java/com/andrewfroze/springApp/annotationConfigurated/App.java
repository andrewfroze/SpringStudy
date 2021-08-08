package com.andrewfroze.springApp.annotationConfigurated;

import com.andrewfroze.springApp.annotationConfigurated.configuration.AppConfig;
import com.andrewfroze.springApp.annotationConfigurated.entity.Client;
import com.andrewfroze.springApp.annotationConfigurated.entity.event.Event;
import com.andrewfroze.springApp.annotationConfigurated.entity.event.EventType;
import com.andrewfroze.springApp.annotationConfigurated.logger.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        App app = context.getBean(App.class);

        Event event1 = context.getBean(Event.class);
        event1.setMsg("Some event for 1");

        Event event2 = context.getBean(Event.class);
        event2.setMsg("Some event for 2");

        app.logEvent(EventType.INFO, event1);
        app.logEvent(EventType.ERROR, event2);
    }

    private void logEvent(EventType eventType, Event event) throws IOException {
        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }
}
