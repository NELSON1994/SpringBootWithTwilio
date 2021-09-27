package com.nelson.twilioexample.twilio;

import com.nelson.twilioexample.twilio.config.TwilioConfigs;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TwilioApplication {

    @Autowired
    private TwilioConfigs twilioConfigs;

    //initialize twilio at start up of the application
    @PostConstruct
    public  void initTwilio(){
        System.out.println("ÜSERNAME : "+twilioConfigs.getAccountSid());
        Twilio.init(twilioConfigs.getAccountSid(),twilioConfigs.getAuthToken());
    }

    public static void main(String[] args) {
        SpringApplication.run(TwilioApplication.class, args);
    }

}
