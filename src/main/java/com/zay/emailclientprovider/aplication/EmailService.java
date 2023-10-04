package com.zay.emailclientprovider.aplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.zay.emailclientprovider.adapters.EmailSenderGateway;
import com.zay.emailclientprovider.core.exceptions.EmailServerException;
import com.zay.emailclientprovider.core.model.Email;

@Service
public class EmailService implements Email{

    private final EmailSenderGateway sparkSendEmail;
    private final EmailSenderGateway amazonSesSendEmail;

    @Autowired
    public EmailService(
        @Qualifier("sparkSendEmail") EmailSenderGateway sparkSendEmail,
        @Qualifier("amazonSesSendEmail") EmailSenderGateway amazonSesSendEmail) {
        this.sparkSendEmail = sparkSendEmail;
        this.amazonSesSendEmail = amazonSesSendEmail;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {

        try {
            this.sparkSendEmail.sendEmail(to, subject, body);
            
        } catch (EmailServerException error) {

            try{
                this.amazonSesSendEmail.sendEmail(to, subject, body);

            } catch (AmazonServiceException errorAmazonServiceException) {

                throw new EmailServerException( "Error send email with Amazon SES: " + errorAmazonServiceException.getErrorMessage());
            }
        }
    }
}
