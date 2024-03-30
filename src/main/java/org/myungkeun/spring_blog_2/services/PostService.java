package org.myungkeun.spring_blog_2.services;


import org.myungkeun.spring_blog_2.payload.post.PostDto;
import org.myungkeun.spring_blog_2.payload.post.PostsResponseDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostDto getPostById(Long id);
    PostDto updatePostById(Long id, PostDto postDto);
    PostsResponseDto getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
    String deletePostById(Long id);
}
