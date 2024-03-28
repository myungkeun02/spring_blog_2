package org.myungkeun.spring_blog_2.services.impl;

import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_2.entities.Role;
import org.myungkeun.spring_blog_2.entities.User;
import org.myungkeun.spring_blog_2.payload.authLogin.AuthLoginRequest;
import org.myungkeun.spring_blog_2.payload.authRegister.AuthRegisterRequest;
import org.myungkeun.spring_blog_2.payload.authRegister.AuthRegisterResponse;
import org.myungkeun.spring_blog_2.repositories.UserRepository;
import org.myungkeun.spring_blog_2.services.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override

    public AuthRegisterResponse registerUser(
            AuthRegisterRequest request
    ) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        AuthRegisterResponse authRegisterResponse = new AuthRegisterResponse();
        authRegisterResponse.setEmail(user.getEmail());
        authRegisterResponse.setUsername(user.getUsername());
        authRegisterResponse.setRole(user.getRole());
        return authRegisterResponse;
    }

    @Override
    public String loginUser(AuthLoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UsernameNotFoundException("User not found (loginUser/findByEmail)"));
        return user.getEmail();
    }
}
