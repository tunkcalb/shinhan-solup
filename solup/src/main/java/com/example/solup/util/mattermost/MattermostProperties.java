package com.example.solup.util.mattermost;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Data
@ConfigurationProperties("notification.mattermost")
@Primary
public class MattermostProperties {

    private String channel;
    private String pretext;
    private String color = "#ff5d52";
    private String authorName = "Back-End Exception";
    private String authorIcon = "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FZYQau%2FbtqJMvCrzlO%2FRtmwZekQC5ZjUtVjwXDt21%2Fimg.png";
    private String title;
    private String text = "";
    private String footer = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
}
