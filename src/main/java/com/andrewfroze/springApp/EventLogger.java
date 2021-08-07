package com.andrewfroze.springApp;

import java.io.IOException;

public interface EventLogger {

    void logEvent(Event event) throws IOException;
}
