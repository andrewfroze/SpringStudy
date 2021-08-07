package com.andrewfroze.springApp;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger{

    private String fileName;
    protected File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void init() throws IOException {
        this.file = new File(fileName);
        if (!file.canWrite()) {
            throw new IOException("File is not available fot writing");
        }
    }

    @Override
    public void logEvent(Event event) throws IOException {
        if(file.exists()) {
            FileUtils.writeStringToFile(file, event.toString()+"\n", true);
        }
    }
}
