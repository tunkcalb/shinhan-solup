package com.example.solup.controller.sms;

import com.example.solup.dto.Response;
import com.example.solup.dto.sms.SmsRequest;
import com.example.solup.dto.sms.RequestDto;
import com.example.solup.dto.sms.SmsResponse;
import com.example.solup.service.sms.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/user/sms")
    public Response<SmsResponse> test(@RequestBody RequestDto request) throws NoSuchAlgorithmException, URISyntaxException, UnsupportedEncodingException, InvalidKeyException, JsonProcessingException {
        SmsResponse data = smsService.sendSms(request.getRecipientPhoneNumber(), request.getContent());
        return new Response<>("200", "sms 요청 성공", data);
    }
}
