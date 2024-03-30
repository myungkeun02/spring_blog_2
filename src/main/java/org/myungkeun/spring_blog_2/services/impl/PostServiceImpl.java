package org.myungkeun.spring_blog_2.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myungkeun.spring_blog_2.entities.Post;
import org.myungkeun.spring_blog_2.exception.ResourceNotFoundException;
import org.myungkeun.spring_blog_2.payload.post.PostDto;
import org.myungkeun.spring_blog_2.payload.post.PostsResponseDto;
import org.myungkeun.spring_blog_2.repositories.PostRepository;
import org.myungkeun.spring_blog_2.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;


    // 게시물 생성
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);
        PostDto response = mapToDto(newPost);
        return response;
    }

    // id로 게시물 찾기
    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GET", "id", id+""));
        PostDto response = mapToDto(post);
        return response;
    }

    @Override
    public PostDto updatePostById(Long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id+ ""));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post updatePost = postRepository.save(post);
        PostDto response = mapToDto(updatePost);
        return response;
    }

    @Override
    public PostsResponseDto getAllPost(int pageNo, int pagezSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pagezSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> contents = listOfPosts.stream().map(post ->
                mapToDto(post)).collect(Collectors.toList());
        PostsResponseDto resopnse = new PostsResponseDto();
        resopnse.setContent(contents);
        resopnse.setPageNo(posts.getNumber() + 1);
        resopnse.setPageSize(posts.getSize());
        resopnse.setTotalPages(posts.getTotalPages());
        resopnse.setTotalElements(posts.getTotalElements());
        resopnse.setLast(posts.isLast());
        return resopnse;
    }

    @Override
    public String deletePostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Get", "id", id + ""));
        postRepository.delete(post);
        return "deleted";
    }
    private PostDto mapToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }
}
