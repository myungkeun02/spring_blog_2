package org.myungkeun.spring_blog_2.exception;

public class PostNotFoundException extends Exception{
    public PostNotFoundException(String message) {
        super(message);
    }
}
