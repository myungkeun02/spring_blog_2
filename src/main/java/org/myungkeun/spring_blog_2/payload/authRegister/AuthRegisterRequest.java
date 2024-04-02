package org.myungkeun.spring_blog_2.payload.authRegister;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterRequest {
    @NotBlank(message = "username is require")
    @Size(min = 3, message = "최소 글자가 3 입니다.")
    @Size(max = 20, message = "최대 글자는 20 입니다.")
    private String username;

    @Email(message = "email is not valid format")
    @NotBlank(message = "email is require")
    private String email;

    @NotBlank(message = "Password is required!")
    @Size(min = 8, max = 20, message = "password must have 8 characters!")
    private String password;
}


//    @Pattern(regexp="^[0-9]*$", message = "Phone number must contain only digits")
