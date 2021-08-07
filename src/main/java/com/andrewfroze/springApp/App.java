package com.andrewfroze.springApp;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("BeanConfiguration.xml");

        App app = (App) context.getBean("app");

        Event event1 = context.getBean(Event.class);
        event1.setMsg("Some event for 1");

        Event event2 = context.getBean(Event.class);
        event2.setMsg("Some event for 2");

        app.logEvent(event1);
        app.logEvent(event2);

         context.close();
    }

    private void logEvent(Event event) throws IOException {
        event.setMsg(event.getMsg().replaceAll(client.getId(), client.getFullName()));
        eventLogger.logEvent(event);
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }
}
