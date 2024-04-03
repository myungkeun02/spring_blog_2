package org.myungkeun.spring_blog_2.services;

import org.myungkeun.spring_blog_2.exception.UserNotFoundException;
import org.myungkeun.spring_blog_2.exception.UserServiceLogicException;
import org.myungkeun.spring_blog_2.payload.api.ApiResponseDto;
import org.myungkeun.spring_blog_2.payload.updataPassword.UpdatePasswordRequest;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface UserService {
    ResponseEntity<ApiResponseDto<?>> getUserInfoByToken(Principal connectedUser) throws UserNotFoundException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> updatePassword( Principal connectedUser, UpdatePasswordRequest request) throws UserNotFoundException, UserServiceLogicException;
}