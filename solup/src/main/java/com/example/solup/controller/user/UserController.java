package com.example.solup.controller.user;

import com.example.solup.dto.UserDto;
import com.example.solup.entity.User;
import com.example.solup.service.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.DuplicateFormatFlagsException;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto) {
        try{
            return ResponseEntity.ok(userService.save(userDto));
        } catch (DuplicateFormatFlagsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate username");
        }
    }

    @GetMapping("/user/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto){
        try{
            return ResponseEntity.ok(userService.login(userDto));
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }

    }
}