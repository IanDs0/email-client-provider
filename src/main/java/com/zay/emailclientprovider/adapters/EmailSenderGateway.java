package com.zay.emailclientprovider.adapters;

public interface EmailSenderGateway {
    public void sendEmail(String to, String subject, String body);
}
