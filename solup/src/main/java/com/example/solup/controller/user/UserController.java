package com.example.solup.controller.user;

import com.example.solup.dto.Response;
import com.example.solup.dto.StaffDto;
import com.example.solup.dto.revenue.RevenueAnalysisDto;
import com.example.solup.dto.store.StoreDto;
import com.example.solup.dto.user.UserDto;
import com.example.solup.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user/signup")
    public Response<UserDto> signup(@RequestBody UserDto userDto) {
        UserDto response = userService.save(userDto);
        return new Response<>("201", "회원가입 성공", response);
    }

    @PostMapping("/user/login")
    public Response<UserDto> login(@RequestBody UserDto userDto) {
        UserDto response = userService.login(userDto);
        return new Response<>("200", "로그인 성공", response);
    }

    @GetMapping("/user/check/{username}")
    public Response<String> checkUsername(@PathVariable String username) {
        String response = userService.findByUsername(username);
        return new Response<>("200", "", response);
    }

    @GetMapping("/user/revenue/analysis/{userId}")
    public Response<RevenueAnalysisDto.Response> getRevenueAnalysis(@PathVariable long userId) {
        RevenueAnalysisDto.Response response = userService.getRevenueAnalysis(userId);
        return new Response<>("200", "매출 분석 완료", response);
    }

    @PostMapping("/user/store/{userId}")
    public Response<StoreDto.Response> registStore(@PathVariable Long userId, @RequestBody StoreDto.Request request) {
        StoreDto.Response response = userService.registStore(userId, request);
        return new Response<>("200", "가게 등록 성공", response);
    }

    @PostMapping("/user/staff/{userId}")
    public Response<StaffDto.Response> registStaff(@PathVariable("userId") Long userId, @RequestBody StaffDto.Request request) {
        StaffDto.Response response = userService.registStaff(userId, request);
        return new Response<>("201", "스태프 등록 성공", response);
    }
}
