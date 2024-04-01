package org.myungkeun.spring_blog_2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_2.payload.updataPassword.UpdatePasswordRequest;
import org.myungkeun.spring_blog_2.payload.userInfo.UserInfoResponse;
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
    public ResponseEntity<UserInfoResponse> getUserInfoByToken(Principal connectedUser) {
        return ResponseEntity.ok(userService.getUserInfoByToken(connectedUser));
    }
    @PatchMapping("/change/password")
    public ResponseEntity<String> updatePassword(
            Principal connectedUser,
            @RequestBody UpdatePasswordRequest request
    ) {
        return ResponseEntity.ok(userService.updatePassword(connectedUser, request));
    }

}








//    @GetMapping()
//    public ResponseEntity<UserInfoResponse> getUserInfoByToken(HttpServletRequest request, HttpServletResponse response) {
//        return ResponseEntity.ok(userService.getUserInfoByToken(request, response));
//    }