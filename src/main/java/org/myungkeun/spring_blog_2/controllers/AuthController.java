package org.myungkeun.spring_blog_2.controllers;

import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_2.payload.authLogin.AuthLoginRequest;
import org.myungkeun.spring_blog_2.payload.authRegister.AuthRegisterRequest;
import org.myungkeun.spring_blog_2.payload.authRegister.AuthRegisterResponse;
import org.myungkeun.spring_blog_2.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    //회원가입 api
    @PostMapping("/register")
    public ResponseEntity<AuthRegisterResponse> registerUser(AuthRegisterRequest request) {
        return new ResponseEntity<>(authService.registerUser(request), HttpStatus.CREATED);
    }

    //로그인 api
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(AuthLoginRequest request) {
        return new ResponseEntity<>(authService.loginUser(request), HttpStatus.OK);
    }
}
