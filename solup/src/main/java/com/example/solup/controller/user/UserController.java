package com.example.solup.controller.user;

import com.example.solup.dto.UserDto;
import com.example.solup.entity.User;
import com.example.solup.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto) {
        UserDto user = userService.save(userDto);
        System.out.println(user.toString());
        return ResponseEntity.ok(user);
    }
}
