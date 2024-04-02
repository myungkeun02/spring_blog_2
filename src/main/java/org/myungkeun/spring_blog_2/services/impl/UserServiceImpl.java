package org.myungkeun.spring_blog_2.services.impl;

import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_2.entities.User;
import org.myungkeun.spring_blog_2.exception.UserAlreadyExistsException;
import org.myungkeun.spring_blog_2.exception.UserNotFoundException;
import org.myungkeun.spring_blog_2.exception.UserServiceLogicException;
import org.myungkeun.spring_blog_2.payload.ApiResponseDto;
import org.myungkeun.spring_blog_2.payload.ApiResponseStatusDto;
import org.myungkeun.spring_blog_2.payload.updataPassword.UpdatePasswordRequest;
import org.myungkeun.spring_blog_2.payload.userInfo.UserInfoResponse;
import org.myungkeun.spring_blog_2.repositories.UserRepository;
import org.myungkeun.spring_blog_2.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<ApiResponseDto<?>> getUserInfoByToken(Principal connectedUser) throws UserNotFoundException, UserServiceLogicException {

            var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
            var userInfoResponse = UserInfoResponse.builder()
                    .email(user.getUsername())
                    .username(user.getUsername())
                    .role(user.getRole())
                    .build();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(200, ApiResponseStatusDto.SUCCESS.name(), userInfoResponse));


    }

    public String updatePassword(Principal connectedUser, UpdatePasswordRequest request) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new IllegalStateException("Password do not match");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return "update password";
    }
}
