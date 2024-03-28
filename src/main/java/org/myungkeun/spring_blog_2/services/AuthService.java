package org.myungkeun.spring_blog_2.services;

import org.myungkeun.spring_blog_2.payload.authLogin.AuthLoginRequest;
import org.myungkeun.spring_blog_2.payload.authLogin.AuthLoginResponse;
import org.myungkeun.spring_blog_2.payload.authRegister.AuthRegisterRequest;
import org.myungkeun.spring_blog_2.payload.authRegister.AuthRegisterResponse;

public interface AuthService {
    AuthRegisterResponse registerUser(AuthRegisterRequest request);

    AuthLoginResponse loginUser(AuthLoginRequest request);
}
