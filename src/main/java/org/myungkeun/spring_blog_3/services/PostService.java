package org.myungkeun.spring_blog_3.services;

import org.myungkeun.spring_blog_3.dto.api.ApiResponseDto;
import org.myungkeun.spring_blog_3.dto.post.PostDto;
import org.springframework.http.ResponseEntity;

public interface PostService {
    ResponseEntity<ApiResponseDto<?>> createPost(PostDto request);

    ResponseEntity<ApiResponseDto<?>> getPostById(long id);

    ResponseEntity<ApiResponseDto<?>> updatePostById(long id, PostDto request);

    ResponseEntity<ApiResponseDto<?>> deletePostById(long id);

    ResponseEntity<ApiResponseDto<?>> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
