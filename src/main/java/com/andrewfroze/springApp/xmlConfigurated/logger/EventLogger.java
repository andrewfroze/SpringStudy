package com.andrewfroze.springApp.xmlConfigurated.logger;

import com.andrewfroze.springApp.xmlConfigurated.entity.event.Event;

import java.io.IOException;

public interface EventLogger {

    void logEvent(Event event) throws IOException;
}
