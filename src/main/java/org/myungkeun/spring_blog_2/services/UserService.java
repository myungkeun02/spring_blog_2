package org.myungkeun.spring_blog_2.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.myungkeun.spring_blog_2.payload.updataPassword.UpdatePasswordRequest;
import org.myungkeun.spring_blog_2.payload.userInfo.UserInfoResponse;

import java.security.Principal;

public interface UserService {
    //    UserInfoResponse getUserInfoByToken(HttpServletRequest request, HttpServletResponse response);
    UserInfoResponse getUserInfoByToken(Principal connectedUser);

    String updatePassword(UpdatePasswordRequest request, Principal connectedUser);

}
