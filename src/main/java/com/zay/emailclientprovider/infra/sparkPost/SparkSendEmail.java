package com.zay.emailclientprovider.infra.sparkPost;    

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zay.emailclientprovider.adapters.EmailSenderGateway;
import com.zay.emailclientprovider.core.exceptions.EmailServerException;
import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.responses.Response;

@Service
public class SparkSendEmail implements EmailSenderGateway {

    private final Client client;

    @Autowired
    public SparkSendEmail(Client client) {
        this.client = client;
    }
    
    @Override
    public void sendEmail(String to, String subject, String body) {
        
        String from = "ian@email.thisistheway.com.br";
        String html = "<html><body><p>"+body+"</p><p>Testing SparkPost - the most awesomest email service!</p></body></html>";
        
        try {
            Response response = client.sendMessage(
                from,
                to,
                subject,
                "",
                html
            );
            System.out.println(response);
        }
        catch (SparkPostException e) {
            throw new EmailServerException(e.getMessage());
        }
    }    
}
