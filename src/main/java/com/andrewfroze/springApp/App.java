package com.andrewfroze.springApp;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("BeanConfiguration.xml");

        App app = (App) context.getBean("app");

        Event event1 = context.getBean(Event.class);
        event1.setMsg("Some event for 1");

        Event event2 = context.getBean(Event.class);
        event2.setMsg("Some event for 2");

        app.logEvent(EventType.INFO, event1);
        app.logEvent(EventType.ERROR, event2);

         context.close();
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
