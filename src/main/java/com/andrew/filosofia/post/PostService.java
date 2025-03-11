package com.andrew.filosofia.post;

import com.andrew.filosofia.post.dto.PostDTO;

public class PostService {
    PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }



}
