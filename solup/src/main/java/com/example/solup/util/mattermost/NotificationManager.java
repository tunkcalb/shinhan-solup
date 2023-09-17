package com.example.solup.util.mattermost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class NotificationManager {

    private Logger log = LoggerFactory.getLogger(NotificationManager.class);

    @Autowired
    private MattermostSender mmSender;

    public void sendNotification(Exception e, String uri, String params) {
        log.info("#### SEND Notification");
        mmSender.sendMessage(e, uri, params);
    }
}
