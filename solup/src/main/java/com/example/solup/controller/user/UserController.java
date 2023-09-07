package com.example.solup.controller.user;

import com.example.solup.dto.Response;
import com.example.solup.dto.UserDto;
import com.example.solup.entity.User;
import com.example.solup.service.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.DuplicateFormatFlagsException;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user/signup")
    public Response<UserDto> signup(@RequestBody UserDto userDto) throws Exception{
            UserDto response = userService.save(userDto);
            return new Response<>("201", "회원가입 성공", response);
    }

    @GetMapping("/user/login")
    public Response<UserDto> login(@RequestBody UserDto userDto) throws Exception{
             UserDto response = userService.login(userDto);
             return new Response<>("200", "로그인 성공", response);
    }

    @GetMapping("/user/check")
    public Response<String> checkUsername(@RequestBody String username){
        String response = userService.findByUsername(username);
        return new Response<>("200", "", response);
    }
}
