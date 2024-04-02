package org.myungkeun.spring_blog_2.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_2.entities.Role;
import org.myungkeun.spring_blog_2.entities.Token;
import org.myungkeun.spring_blog_2.entities.TokenType;
import org.myungkeun.spring_blog_2.entities.User;
import org.myungkeun.spring_blog_2.exception.UserAlreadyExistsException;
import org.myungkeun.spring_blog_2.exception.UserNotFoundException;
import org.myungkeun.spring_blog_2.exception.UserServiceLogicException;
import org.myungkeun.spring_blog_2.jwt.JwtService;
import org.myungkeun.spring_blog_2.payload.ApiResponseDto;
import org.myungkeun.spring_blog_2.payload.ApiResponseStatusDto;
import org.myungkeun.spring_blog_2.payload.authLogin.AuthLoginRequest;
import org.myungkeun.spring_blog_2.payload.authLogin.AuthLoginResponse;
import org.myungkeun.spring_blog_2.payload.authRegister.AuthRegisterRequest;
import org.myungkeun.spring_blog_2.payload.authRegister.AuthRegisterResponse;
import org.myungkeun.spring_blog_2.repositories.TokenRepository;
import org.myungkeun.spring_blog_2.repositories.UserRepository;
import org.myungkeun.spring_blog_2.services.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override

    public ResponseEntity<ApiResponseDto<?>> registerUser(
            AuthRegisterRequest request
    ) throws UserAlreadyExistsException, UserServiceLogicException {
        try {
            User user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            userRepository.save(user);

            AuthRegisterResponse response = AuthRegisterResponse.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .build();

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(201, ApiResponseStatusDto.SUCCESS.name(), response));
        } catch (Exception e) {
            throw new UserServiceLogicException();
        }

    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> loginUser(AuthLoginRequest request) throws UserNotFoundException, UserServiceLogicException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(()-> new UsernameNotFoundException("User not found (loginUser/findByEmail)"));
            System.out.println(user);
            var accessToken = jwtService.generateAccessToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
//            saveUserToken(user, accessToken, refreshToken);
            AuthLoginResponse response = AuthLoginResponse.builder()
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .role(user.getRole())
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(200, ApiResponseStatusDto.SUCCESS.name(), response));
        } catch (Exception e) {
            throw new UserServiceLogicException();
        }

    }

    private void saveUserToken(User user, String accessToken, String refreshToken) {
        var token = Token.builder()
                .user(user)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserToken = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserToken.isEmpty()) {
            return;
        }
        validUserToken.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String email;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authHeader.substring(7);
        email = jwtService.extractUsername(refreshToken);

        if (email != null) {
            User user = this.userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateAccessToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken, refreshToken);
                var authResponse = AuthLoginResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}


