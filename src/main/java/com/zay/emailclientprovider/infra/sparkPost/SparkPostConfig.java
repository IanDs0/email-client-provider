package com.zay.emailclientprovider.infra.sparkPost;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sparkpost.Client;

@Configuration
public class SparkPostConfig {
    
    @Value("${spark_post.secretKey}")
    private String secretKey;
    
    @Bean
    public Client client(){
        return new Client(secretKey);
    }
}
