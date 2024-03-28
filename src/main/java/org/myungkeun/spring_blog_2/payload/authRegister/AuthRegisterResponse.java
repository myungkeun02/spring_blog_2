package org.myungkeun.spring_blog_2.payload.authRegister;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myungkeun.spring_blog_2.entities.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterResponse {
    private String username;
    private String email;
    private Role role;
}
