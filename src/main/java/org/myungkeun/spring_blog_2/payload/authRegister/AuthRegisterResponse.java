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
    // res에서 왜 username이 email로 리턴이 되는걸까..
    private String username;
    private String email;
    private Role role;
}
