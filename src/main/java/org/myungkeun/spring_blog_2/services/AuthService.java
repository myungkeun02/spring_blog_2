package org.myungkeun.spring_blog_2.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.myungkeun.spring_blog_2.payload.authLogin.AuthLoginRequest;
import org.myungkeun.spring_blog_2.payload.authLogin.AuthLoginResponse;
import org.myungkeun.spring_blog_2.payload.authRegister.AuthRegisterRequest;
import org.myungkeun.spring_blog_2.payload.authRegister.AuthRegisterResponse;

import java.io.IOException;

public interface AuthService {
    AuthRegisterResponse registerUser(AuthRegisterRequest request);

    AuthLoginResponse loginUser(AuthLoginRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
