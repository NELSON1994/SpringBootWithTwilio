package com.nelson.twilioexample.twilio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResponseDto {
    private String message;
    private OtpStatus otpStatus;
}
