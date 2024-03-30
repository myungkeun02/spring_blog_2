package org.myungkeun.spring_blog_2.payload.userInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myungkeun.spring_blog_2.entities.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoResponse {
    private String username;
    private String email;
    private Role role;
}
