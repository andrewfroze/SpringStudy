package com.andrewfroze.springApp.annotationConfigurated.logger;

import com.andrewfroze.springApp.annotationConfigurated.entity.event.Event;

import java.io.IOException;
import java.util.Collection;

public class CombinedEventLogger implements EventLogger {
    private Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        loggers.forEach(logger -> {
            try {
                logger.logEvent(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
