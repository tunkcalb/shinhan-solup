package com.example.solup.dto.sms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SmsRequest {
    private String recipientPhoneNumber;
    private String title;
    private String content;

}
