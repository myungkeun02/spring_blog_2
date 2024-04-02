package org.myungkeun.spring_blog_2.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.myungkeun.spring_blog_2.exception.UserAlreadyExistsException;
import org.myungkeun.spring_blog_2.exception.UserNotFoundException;
import org.myungkeun.spring_blog_2.exception.UserServiceLogicException;
import org.myungkeun.spring_blog_2.payload.ApiResponseDto;
import org.myungkeun.spring_blog_2.payload.updataPassword.UpdatePasswordRequest;
import org.myungkeun.spring_blog_2.payload.userInfo.UserInfoResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface UserService {
    ResponseEntity<ApiResponseDto<?>> getUserInfoByToken(Principal connectedUser) throws UserNotFoundException, UserServiceLogicException;

    String updatePassword( Principal connectedUser, UpdatePasswordRequest request);

}


//UserInfoResponse getUserInfoByToken(HttpServletRequest request, HttpServletResponse response);