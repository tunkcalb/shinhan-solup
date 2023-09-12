package com.example.solup.exception;

import com.example.solup.exception.type.CustomException;
import com.example.solup.util.mattermost.NotificationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final NotificationManager notificationManager;

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final CustomException e,
                                                                    HttpServletRequest req) {

        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        e.printStackTrace();
        response.setMessage(e.getMessage());
        notificationManager.sendNotification(e, req.getRequestURI(), getParams(req));
        return new ResponseEntity<>(response,
                HttpStatus.valueOf(Integer.parseInt(errorCode.getStatus())));
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest req) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        e.printStackTrace();
        response.setMessage(e.getMessage());
        notificationManager.sendNotification(e, req.getRequestURI(), getParams(req));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getParams(HttpServletRequest req) {
        StringBuilder params = new StringBuilder();
        Enumeration<String> keys = req.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            params.append("- ").append(key).append(" : ").append(req.getParameter(key))
                    .append("\n");
        }

        return params.toString();
    }

}
