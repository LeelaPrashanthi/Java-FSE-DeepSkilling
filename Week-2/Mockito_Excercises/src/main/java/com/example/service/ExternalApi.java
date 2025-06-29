package com.example.service;

public interface ExternalApi {
    String getData();
    String getUserData(String userId);
    void logAction(String message);
    String start();
    String process();
    String finish();


}
