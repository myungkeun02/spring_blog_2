package org.myungkeun.spring_blog_2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_2.exception.UserAlreadyExistsException;
import org.myungkeun.spring_blog_2.exception.UserNotFoundException;
import org.myungkeun.spring_blog_2.exception.UserServiceLogicException;
import org.myungkeun.spring_blog_2.payload.ApiResponseDto;
import org.myungkeun.spring_blog_2.payload.authLogin.AuthLoginRequest;
import org.myungkeun.spring_blog_2.payload.authLogin.AuthLoginResponse;
import org.myungkeun.spring_blog_2.payload.authRegister.AuthRegisterRequest;
import org.myungkeun.spring_blog_2.payload.authRegister.AuthRegisterResponse;
import org.myungkeun.spring_blog_2.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    //회원가입 api
    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<?>> registerUser(
            @RequestBody AuthRegisterRequest request
    ) throws UserAlreadyExistsException, UserServiceLogicException {
//        return new ResponseEntity<>(authService.registerUser(request), HttpStatus.CREATED);
        return authService.registerUser(request);
    }

    //로그인 api
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<?>> loginUser(
            @RequestBody AuthLoginRequest request
    ) throws UserNotFoundException, UserServiceLogicException {
        return authService.loginUser(request);
    }

    @PostMapping("/refresh")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authService.refreshToken(request, response);
    }
}
