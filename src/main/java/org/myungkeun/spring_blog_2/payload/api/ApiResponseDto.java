package org.myungkeun.spring_blog_2.payload.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseDto<T> {
    private int status;
    private String message;
    private T body;
}
