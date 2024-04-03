package org.myungkeun.spring_blog_2.payload.updataPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UpdatePasswordRequest {
    @NotBlank(message = "email is required!")
    @Email(message = "email is not valid format")
    private String currentPassword;

    @NotBlank(message = "email is required!")
    @Email(message = "email is not valid format")
    private String newPassword;

    @NotBlank(message = "email is required!")
    @Email(message = "email is not valid format")
    private String confirmPassword;
}
