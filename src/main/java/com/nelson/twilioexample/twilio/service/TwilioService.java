package com.nelson.twilioexample.twilio.service;

import com.nelson.twilioexample.twilio.config.TwilioConfigs;
import com.nelson.twilioexample.twilio.dto.OtpStatus;
import com.nelson.twilioexample.twilio.dto.PasswordRequestDto;
import com.nelson.twilioexample.twilio.dto.PasswordResponseDto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwilioService {
    @Autowired
    private TwilioConfigs twilioConfigs;

    Map<String, String> otpMap=new HashMap<>();
    public Mono<PasswordResponseDto> sendOTPforPasswordReset(PasswordRequestDto passwordRequestDto){
        PasswordResponseDto passwordResponseDto = new PasswordResponseDto();
        try {

            PhoneNumber to = new PhoneNumber(passwordRequestDto.getPHonenumber());
            PhoneNumber from = new PhoneNumber(twilioConfigs.getTrialNumber());

            String otp = generateOtp();
            String otpMessage = "Dear Customer, Your One Time Password is : " + otp + " . Thank you and welcome Next Time";
            Message message = Message
                    .creator(to, // to
                            from, // from
                            otpMessage)// message
                    .create();

            otpMap.put(passwordRequestDto.getUsername(),otp);
            passwordResponseDto.setOtpStatus(OtpStatus.DELIVERED);
            passwordResponseDto.setMessage(otpMessage);
        }
        catch(Exception ex){
          ex.printStackTrace();
            passwordResponseDto.setOtpStatus(OtpStatus.FAILED);
            passwordResponseDto.setMessage(ex.getMessage());
        }
        return Mono.just(passwordResponseDto);
    }
//validate otp
    public Mono<String> validateOtp(String otp, String username){
        if(otp.equals(otpMap.get(username))){
            return Mono.just("Valid Otp , proceed");
        }
        else{
            return Mono.error(new IllegalArgumentException("Invalid OTP,Please Retry !!!!!"));
        }
    }
    //set 6 digits otp
    private String generateOtp(){
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
}
