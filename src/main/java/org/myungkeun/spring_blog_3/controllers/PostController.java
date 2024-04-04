package org.myungkeun.spring_blog_3.controllers;

import lombok.RequiredArgsConstructor;
import org.myungkeun.spring_blog_3.dto.api.ApiResponseDto;
import org.myungkeun.spring_blog_3.dto.post.PostDto;
import org.myungkeun.spring_blog_3.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/")
    public ResponseEntity<ApiResponseDto<?>> createPost(
            @RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<?>> getPostById(
            @PathVariable Long id
    ) {
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<?>> updatePostById(
            @PathVariable Long id,
            @RequestBody PostDto postDto
    ) {
        return postService.updatePostById(id, postDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<?>> deletePostById(
            @PathVariable Long id
    ) {
        return postService.deletePostById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponseDto<?>> getAllPost(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }
}

