package com.nelson.twilioexample.twilio.dto;

import lombok.Data;

@Data
public class PasswordRequestDto {
    private String pHonenumber;//destination
    private String username;
    private String otp;
}
