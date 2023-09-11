package com.example.solup.controller.user;

import com.example.solup.dto.Response;
import com.example.solup.dto.StaffDto;
import com.example.solup.dto.revenue.RevenueAnalysisDto;
import com.example.solup.dto.store.StoreDto;
import com.example.solup.dto.user.LoginDto;
import com.example.solup.dto.user.RegistAccountDto;
import com.example.solup.dto.user.SignupDto;
import com.example.solup.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/user/signup")
    public Response<SignupDto.Response> signup(@RequestBody SignupDto.Request request) {
        SignupDto.Response response = userService.save(request);
        return new Response<>("201", "회원가입 성공", response);
    }
    
    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/user/login")
    public Response<LoginDto.Response> login(@RequestBody LoginDto.Request request) {
        LoginDto.Response response = userService.login(request);
        return new Response<>("200", "로그인 성공", response);
    }
    
    @Operation(summary = "이름 중복 체크", description = "이름 중복 체크")
    @GetMapping("/user/check/{username}")
    public Response<String> checkUsername(@PathVariable String username) {
        String response = userService.findByUsername(username);
        return new Response<>("200", "", response);
    }
    
    @Operation(summary = "매출 분석", description = "매출 분석")
    @GetMapping("/user/revenue/analysis/{userId}")
    public Response<RevenueAnalysisDto.Response> getRevenueAnalysis(@PathVariable Long userId) {
        RevenueAnalysisDto.Response response = userService.getRevenueAnalysis(userId);
        return new Response<>("200", "매출 분석 완료", response);
    }
    
    @Operation(summary = "가게 등록", description = "가게 등록")
    @PostMapping("/user/store/{userId}")
    public Response<StoreDto.Response> registStore(@PathVariable Long userId, @RequestBody StoreDto.Request request) {
        StoreDto.Response response = userService.registStore(userId, request);
        return new Response<>("201", "가게 등록 성공", response);
    }
    
    @Operation(summary = "계좌 등록", description = "계좌 등록")
    @PostMapping("/user/account/{userId}")
    public Response<RegistAccountDto.Response> registAccount(@PathVariable Long userId, @RequestBody RegistAccountDto.Request request){
        RegistAccountDto.Response response = userService.registAccount(userId, request);
        return new Response<>("201", "계좌 등록 성공", response);
    }

    @Operation(summary = "직원 등록", description = "직원 등록")
    @PostMapping("/user/staff/{userId}")
    public Response<StaffDto.Response> registStaff(@PathVariable("userId") Long userId, @RequestBody StaffDto.Request request) {
        StaffDto.Response response = userService.registStaff(userId, request);
        return new Response<>("201", "스태프 등록 성공", response);
    }
    
    @Operation(summary = "직원 목록 조회", description = "직원 목록 조회")
    @GetMapping("user/staff/{userId}")
    public Response<List<StaffDto.Response>> getStaffes(@PathVariable("userId") Long userId) {
        List<StaffDto.Response> responses = userService.getStaffes(userId);
        return new Response<>("200", "스태프 조회 성공", responses);
    }
}
