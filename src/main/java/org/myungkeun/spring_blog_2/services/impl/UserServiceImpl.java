package org.myungkeun.spring_blog_2.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_2.entities.User;
import org.myungkeun.spring_blog_2.jwt.JwtService;
import org.myungkeun.spring_blog_2.payload.updataPassword.UpdatePasswordRequest;
import org.myungkeun.spring_blog_2.payload.userInfo.UserInfoResponse;
import org.myungkeun.spring_blog_2.repositories.UserRepository;
import org.myungkeun.spring_blog_2.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
//
//    public UserInfoResponse getUserInfoByToken(Principal connectedUser) {
//        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
//        var userInfoResponse = UserInfoResponse.builder()
//                .email(user.getUsername())
//                .username(user.getUsername())
//                .role(user.getRole())
//                .build();
//        return userInfoResponse;
//    }
//
//    public String updatePassword(UpdatePasswordRequest request, Principal connectedUser) {
//        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
//        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
//            throw new IllegalStateException("Wrong password");
//        }
//        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
//            throw new IllegalStateException("Password do not match");
//        }
//        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//        userRepository.save(user);
//        return "update password";
//    }

        public UserInfoResponse getUserInfoByToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String email;
        final String accessToken;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("No token");
        }
        accessToken = authHeader.substring(7);
        email = jwtService.extractUsername(accessToken);
        var user = this.userRepository.findByEmail(email)
                .orElseThrow();
        var userInfoResponse = UserInfoResponse.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
        return userInfoResponse;
    }

}



