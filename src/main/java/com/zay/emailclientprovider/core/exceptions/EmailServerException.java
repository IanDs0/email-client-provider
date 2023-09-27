package com.zay.emailclientprovider.core.exceptions;

public class EmailServerException extends RuntimeException {

    public EmailServerException(String message) {
        super(message);
    }

    public EmailServerException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
