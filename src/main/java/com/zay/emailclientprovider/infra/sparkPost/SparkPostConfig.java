package com.zay.emailclientprovider.infra.sparkPost;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sparkpost.Client;

@Configuration
public class SparkPostConfig {
    
    @Value("${SPARK_POST}")
    private String SPARK_POST;
    
    @Bean
    public Client client(){
        return new Client(SPARK_POST);
    }
}
