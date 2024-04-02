package org.myungkeun.spring_blog_2.payload.authLogin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myungkeun.spring_blog_2.entities.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginResponse {
    private String username;
    private String email;
    private Role role;
    private String accessToken;
    private String refreshToken;
}
