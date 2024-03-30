package org.myungkeun.spring_blog_2.controllers;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto
    ) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(
            @PathVariable(name = "id") Long id
    ) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<PostsResponseDto> getAllPost(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return new ResponseEntity<>(postService.getAllPost(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(
            @RequestBody PostDto postDto,
            @PathVariable(name = "id") Long id
    ) {
        return new ResponseEntity<>(postService.updatePostById(id, postDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(
            @PathVariable(name = "id") Long id
    ) {
        return new ResponseEntity<>(postService.deletePostById(id), HttpStatus.OK);
    }
}
