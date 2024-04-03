package org.myungkeun.spring_blog_2.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.myungkeun.spring_blog_2.exception.UserAlreadyExistsException;
import org.myungkeun.spring_blog_2.exception.UserNotFoundException;
import org.myungkeun.spring_blog_2.exception.UserServiceLogicException;
import org.myungkeun.spring_blog_2.payload.api.ApiResponseDto;
import org.myungkeun.spring_blog_2.payload.authLogin.AuthLoginRequest;
import org.myungkeun.spring_blog_2.payload.authRegister.AuthRegisterRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthService {
    ResponseEntity<ApiResponseDto<?>> registerUser(AuthRegisterRequest request) throws UserAlreadyExistsException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> loginUser(AuthLoginRequest request) throws UserNotFoundException, UserServiceLogicException;

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
