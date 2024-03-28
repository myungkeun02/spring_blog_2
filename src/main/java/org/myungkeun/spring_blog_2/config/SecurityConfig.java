package org.myungkeun.spring_blog_2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String[] WHITE_LIST_URL = {
            "/**",
            "/api/**"
    };

    private final AuthenticationProvider authenticationProvider;

    //보안필터체인 설정 / 애플리케이션의 보안을 관리하고 요청에 대한 인증 및 권한 부여를 구현
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll())
                .sessionManagement(sesstion ->
                        sesstion.sessionCreationPolicy(SessionCreationPolicy
                                .STATELESS))
                .authenticationProvider(authenticationProvider);
        return httpSecurity.build();
    }
}
