package org.myungkeun.spring_blog_2.payload.authLogin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginRequest {
    @NotBlank(message = "email is require")
    private String email;
    @NotBlank(message = "password is require")
    private String password;
}
