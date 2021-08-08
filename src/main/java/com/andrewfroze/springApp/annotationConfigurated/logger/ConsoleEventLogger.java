package com.andrewfroze.springApp.annotationConfigurated.logger;

import com.andrewfroze.springApp.annotationConfigurated.entity.event.Event;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event);
    }
}
