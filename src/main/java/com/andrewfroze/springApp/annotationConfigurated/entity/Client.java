package com.andrewfroze.springApp.annotationConfigurated.entity;

import org.springframework.beans.factory.annotation.Value;

public class Client {

    @Value("${client.id}")
    private String id;

    @Value("${client.name}")
    private String fullName;

    @Value("${client.greeting}")
    private String greeting;

    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
