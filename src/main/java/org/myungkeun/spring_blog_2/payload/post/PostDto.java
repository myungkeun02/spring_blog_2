package org.myungkeun.spring_blog_2.payload.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank (message = "제목이 비어있습니다.")
    @Size (min = 1, max = 50, message = "제목은 1~50자로 작성해야합니다")
    private String title;
    @NotBlank (message = "내용이 비어있습니다.")
    @Size (min = 1, max = 250, message = "내용은 1~250자로 작성해야합니다")
    private String content;
}
