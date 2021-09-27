package com.nelson.twilioexample.twilio.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

//ACT AS CONTROLLER CLASS
@Configuration
public class TwilioRouterConfig {
    @Autowired
    private TwilioOtpHandler twilioOtpHandler;

    @Bean
    public RouterFunction<ServerResponse> handleSMS(){
        return RouterFunctions.route()
                .POST("/router/sendOTP",twilioOtpHandler::sendOtp)
                .POST("/router/validateOTP",twilioOtpHandler::validateOtp)
                .build();
    }
}
