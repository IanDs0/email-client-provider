package com.zay.emailclientprovider.core.model;

public interface Email {
    public void sendEmail(String to, String subject, String body);
}
