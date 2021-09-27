package com.nelson.twilioexample.twilio.resource;

import com.nelson.twilioexample.twilio.dto.PasswordRequestDto;
import com.nelson.twilioexample.twilio.dto.PasswordResponseDto;
import com.nelson.twilioexample.twilio.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TwilioOtpHandler {
    // execute service
    @Autowired
    private TwilioService twilioService;

    public Mono<ServerResponse> sendOtp(ServerRequest serverRequest){
        return serverRequest.bodyToMono(PasswordRequestDto.class)
                .flatMap(dto -> twilioService.sendOTPforPasswordReset(dto))
                .flatMap(dto -> ServerResponse.status(HttpStatus.OK)
                .body(BodyInserters.fromValue(dto)));

    }

    public Mono<ServerResponse> validateOtp(ServerRequest serverRequest){
        return serverRequest.bodyToMono(PasswordRequestDto.class)
                .flatMap(dto -> twilioService.validateOtp(dto.getOtp(),dto.getUsername()))
                .flatMap(dto -> ServerResponse.status(HttpStatus.OK)
                        .bodyValue(dto));
    }
}
