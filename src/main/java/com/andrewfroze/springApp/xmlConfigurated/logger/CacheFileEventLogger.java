package com.andrewfroze.springApp.xmlConfigurated.logger;

import com.andrewfroze.springApp.xmlConfigurated.entity.event.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache = new ArrayList<>();


    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.file = new File(fileName);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    public void writeEventsFromCache() {
        if (file.exists()) {
            cache.forEach(event -> {
                try {
                    FileUtils.writeStringToFile(file, event.toString()+"\n", true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void destroy() {
        if (cache != null && !cache.isEmpty()) {
            writeEventsFromCache();
        }
    }
}
