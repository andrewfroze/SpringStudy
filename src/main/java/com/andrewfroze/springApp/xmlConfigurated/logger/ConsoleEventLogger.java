package com.andrewfroze.springApp.xmlConfigurated.logger;

import com.andrewfroze.springApp.xmlConfigurated.entity.event.Event;

public class ConsoleEventLogger implements EventLogger{

    public void logEvent(Event event) {
        System.out.println(event);
    }
}
