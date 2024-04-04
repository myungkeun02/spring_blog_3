package org.myungkeun.spring_blog_3.services.Impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myungkeun.spring_blog_3.dto.api.ApiResponseDto;
import org.myungkeun.spring_blog_3.dto.api.ApiResponseStatus;
import org.myungkeun.spring_blog_3.dto.post.PostDto;
import org.myungkeun.spring_blog_3.dto.post.PostsDto;
import org.myungkeun.spring_blog_3.entities.Post;
import org.myungkeun.spring_blog_3.repositories.PostRepository;
import org.myungkeun.spring_blog_3.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class PostServiceImpl implements PostService {
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    @Override
    public ResponseEntity<ApiResponseDto<?>> createPost(PostDto request) {
        try {
            Post post = mapToEntity(request);
            Post newPost = postRepository.save(post);
            PostDto response = mapToDto(newPost);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(HttpStatus.CREATED.value(), ApiResponseStatus.SUCCESS.name(), response));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ApiResponseStatus.FAIL.name(), null));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getPostById(long id) {
        try {
            Post post = postRepository.findById(id)
                    .orElseThrow();
            PostDto response = mapToDto(post);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(HttpStatus.OK.value(), ApiResponseStatus.SUCCESS.name(), response));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ApiResponseStatus.FAIL.name(), null));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updatePostById(long id, PostDto request) {
        try {
            Post currentPost = postRepository.findById(id)
                    .orElseThrow();
            currentPost.setTitle(request.getTitle());
            currentPost.setContent(request.getContent());
            Post updatePost = postRepository.save(currentPost);
            PostDto response = mapToDto(updatePost);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(HttpStatus.OK.value(), ApiResponseStatus.SUCCESS.name(), response));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ApiResponseStatus.FAIL.name(), null));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deletePostById(long id) {
        try {
            Post post = postRepository.findById(id)
                    .orElseThrow();
            postRepository.delete(post);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(HttpStatus.OK.value(), ApiResponseStatus.SUCCESS.name(), "success delete"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ApiResponseStatus.FAIL.name(), null));
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> contents = listOfPosts.stream().map(post ->
                mapToDto(post)).collect(Collectors.toList());
        PostsDto response = new PostsDto();
        response.builder()
                .content(contents)
                .pageNo(posts.getNumber())
                .pageSize(posts.getSize())
                .totalPages(posts.getTotalPages())
                .totalElements(posts.getTotalElements())
                .last(posts.isLast());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDto<>(HttpStatus.OK.value(), ApiResponseStatus.SUCCESS.name(), response));
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
