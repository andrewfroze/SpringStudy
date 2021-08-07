package com.andrewfroze.springApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("BeanConfiguration.xml");

        App app = (App) context.getBean("app");

        Event event1 = context.getBean(Event.class);
        event1.setMsg("Some event for 1");

        Event event2 = context.getBean(Event.class);
        event2.setMsg("Some event for 2");

        app.logEvent(event1);
        app.logEvent(event2);
    }

    private void logEvent(Event event) {
        event.setMsg(event.getMsg().replaceAll(client.getId(), client.getFullName()));
        eventLogger.logEvent(event);
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }
}
