package com.andrewfroze.springApp.annotationConfigurated.logger;

import com.andrewfroze.springApp.annotationConfigurated.entity.event.Event;

import java.io.IOException;

public interface EventLogger {

    void logEvent(Event event) throws IOException;
}
