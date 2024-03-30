package org.myungkeun.spring_blog_2.payload.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// post 단일 req, res DTO
public class PostDto {
    private String title;
    private String content;
}
