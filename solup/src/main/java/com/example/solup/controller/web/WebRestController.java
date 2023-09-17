package com.example.solup.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class WebRestController {
    private final Environment env;

    @GetMapping("/profile")
    public String getProfile(){
        return Arrays.stream(env.getActiveProfiles()).findFirst().orElse("");
    }

    @GetMapping("/health")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public void isOk() {
    }
}
