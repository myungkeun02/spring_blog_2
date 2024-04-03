package org.myungkeun.spring_blog_2.controllers;

import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_2.payload.api.ApiResponseDto;
import org.myungkeun.spring_blog_2.payload.post.PostDto;
import org.myungkeun.spring_blog_2.payload.post.PostsResponseDto;
import org.myungkeun.spring_blog_2.services.PostService;
import org.myungkeun.spring_blog_2.utill.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/")
    public ResponseEntity<ApiResponseDto<?>> createPost(
            @RequestBody PostDto postDto
    ) {
        return postService.createPost(postDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<?>> getPostById(
            @PathVariable(name = "id") Long id
    ) {
        return postService.getPostById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponseDto<?>> getAllPost(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<?>> updatePostById(
            @PathVariable(name = "id") Long id,
            @RequestBody PostDto postDto
    ) {
        return postService.updatePostById(id, postDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<?>> deleteById(
            @PathVariable(name = "id") Long id
    ) {
        return postService.deletePostById(id);
    }
}
