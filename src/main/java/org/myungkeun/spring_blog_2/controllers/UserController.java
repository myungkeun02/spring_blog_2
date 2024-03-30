package org.myungkeun.spring_blog_2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_2.payload.userInfo.UserInfoResponse;
import org.myungkeun.spring_blog_2.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @GetMapping()
    public ResponseEntity<UserInfoResponse> getUserInfoByToken(Principal connectedUser) {
        return ResponseEntity.ok(userService.getUserInfoByToken(connectedUser));
    }
}




//@GetMapping()
//public ResponseEntity<UserInfoResponse> getUserInfoByToken(HttpServletRequest request, HttpServletResponse response) {
//    return ResponseEntity.ok(userService.getUserInfoByToken(request, response));
//}
