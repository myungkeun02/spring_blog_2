package org.myungkeun.spring_blog_2.services;


import org.myungkeun.spring_blog_2.payload.api.ApiResponseDto;
import org.myungkeun.spring_blog_2.payload.post.PostDto;
import org.myungkeun.spring_blog_2.payload.post.PostsResponseDto;
import org.springframework.http.ResponseEntity;

public interface PostService {
    ResponseEntity<ApiResponseDto<?>> createPost(PostDto postDto);
    ResponseEntity<ApiResponseDto<?>> getPostById(Long id);
    ResponseEntity<ApiResponseDto<?>> updatePostById(Long id, PostDto postDto);
    ResponseEntity<ApiResponseDto<?>> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
    ResponseEntity<ApiResponseDto<?>> deletePostById(Long id);
}
