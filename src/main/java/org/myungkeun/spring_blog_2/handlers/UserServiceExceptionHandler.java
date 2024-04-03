package org.myungkeun.spring_blog_2.handlers;

import org.myungkeun.spring_blog_2.exception.PostServiceLogicException;
import org.myungkeun.spring_blog_2.exception.UserAlreadyExistsException;
import org.myungkeun.spring_blog_2.exception.UserNotFoundException;
import org.myungkeun.spring_blog_2.exception.UserServiceLogicException;
import org.myungkeun.spring_blog_2.payload.api.ApiResponseDto;
import org.myungkeun.spring_blog_2.payload.api.ApiResponseStatusDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice

public class UserServiceExceptionHandler {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ApiResponseDto<?>> UserNotFoundExceptionHandler(UserNotFoundException userNotFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDto<>(404, ApiResponseStatusDto.FAIL.name(), userNotFoundException.getMessage()));
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDto<?>> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException userAlreadyExistsException) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiResponseDto<>(409, ApiResponseStatusDto.FAIL.name(), userAlreadyExistsException.getMessage()));
    }

    @ExceptionHandler(value = UserServiceLogicException.class)
    public ResponseEntity<ApiResponseDto<?>> UserServiceLogicExceptionHandler(UserServiceLogicException userServiceLogicException) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponseDto<>(400, ApiResponseStatusDto.FAIL.name(), userServiceLogicException.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<?>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errorMessage = new ArrayList<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.add(error.getDefaultMessage());
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponseDto<>(400, ApiResponseStatusDto.FAIL.name(), errorMessage.toString()));
    }

    @ExceptionHandler(value = PostServiceLogicException.class)
    public ResponseEntity<ApiResponseDto<?>> PostServiceLogicExceptionHandler(PostServiceLogicException postServiceLogicException) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponseDto<>(400, ApiResponseStatusDto.FAIL.name(), postServiceLogicException.getMessage()));
    }




}
