package com.example.solup.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class MyConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri("https://api.example.com") // 기본 URL 설정
                .setConnectTimeout(Duration.ofSeconds(10)) // 연결 타임아웃 설정
                .setReadTimeout(Duration.ofSeconds(10)) // 읽기 타임아웃 설정
                .build();
    }
}
