package com.example.service;


public class MyService {
    private ExternalApi api;

    // Constructor injection
    public MyService(ExternalApi api) {
        this.api = api;
    }

    // Method that uses the ExternalApi
    public String fetchData() {
        return api.getData();
    }

    public String fetchUserData(String userId) {
        return api.getUserData(userId);
    }

    public void doLogging(String msg) {
        api.logAction(msg);
    }

    public void executeAll() {
        api.start();
        api.process();
        api.finish();
    }



}
