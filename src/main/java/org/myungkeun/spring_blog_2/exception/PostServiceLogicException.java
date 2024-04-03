package org.myungkeun.spring_blog_2.exception;

public class PostServiceLogicException extends Exception{
    public PostServiceLogicException() {
        super("Something went wrong, Please try again later!");
    }
}
