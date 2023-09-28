package com.zay.emailclientprovider.infra.amazonSes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.zay.emailclientprovider.adapters.EmailSenderGateway;
import com.zay.emailclientprovider.core.exceptions.EmailServerException;

@Service
public class AmazonSesSendEmail implements EmailSenderGateway {
    
    private final AmazonSimpleEmailService simpleEmailService;

    @Autowired
    public AmazonSesSendEmail(AmazonSimpleEmailService simpleEmailService) {
        this.simpleEmailService = simpleEmailService;
    }    

    @Override
    public void sendEmail(String to, String subject, String body) {

        String from = "ian@email.thisistheway.com.br";
        String html = "<html><body><p>"+body+"</p><p>Testing AmazonSeS - the most awesomest email service!</p></body></html>";

        SendEmailRequest request = new SendEmailRequest()
            .withSource(from)
            .withDestination(new Destination()
                .withToAddresses(to)
            )
            .withMessage(new Message()
                .withSubject(
                    new Content()
                        .withCharset("UTF-8")
                        .withData(subject)
                )
                .withBody(
                    new Body()
                        .withHtml(
                            new Content(html)
                        )
                )
            );
        
            try {
                this.simpleEmailService.sendEmail(request);
            } catch (AmazonServiceException e) {
                throw new EmailServerException("e.getMessage()", e);
            }

    }

}
