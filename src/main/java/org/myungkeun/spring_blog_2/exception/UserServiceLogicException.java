package org.myungkeun.spring_blog_2.exception;

public class UserServiceLogicException extends Exception{
    public UserServiceLogicException() {
        super("Something went wrong, Please try again later!");
    }
}
