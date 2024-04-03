package org.myungkeun.spring_blog_2.controllers;

import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_2.exception.UserNotFoundException;
import org.myungkeun.spring_blog_2.exception.UserServiceLogicException;
import org.myungkeun.spring_blog_2.payload.api.ApiResponseDto;
import org.myungkeun.spring_blog_2.payload.updataPassword.UpdatePasswordRequest;
import org.myungkeun.spring_blog_2.services.AuthService;
import org.myungkeun.spring_blog_2.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping()
    public ResponseEntity<ApiResponseDto<?>> getUserInfoByToken(Principal connectedUser
    ) throws UserNotFoundException, UserServiceLogicException {
        return userService.getUserInfoByToken(connectedUser);
    }
    @PatchMapping("/change/password")
    public ResponseEntity<ApiResponseDto<?>> updatePassword(
            Principal connectedUser,
            @RequestBody UpdatePasswordRequest request
    ) throws UserNotFoundException, UserServiceLogicException{
//        return ResponseEntity.ok(userService.updatePassword(connectedUser, request));
    }

}








//    @GetMapping()
//    public ResponseEntity<UserInfoResponse> getUserInfoByToken(HttpServletRequest request, HttpServletResponse response) {
//        return ResponseEntity.ok(userService.getUserInfoByToken(request, response));
//    }