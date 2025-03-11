package com.andrew.filosofia.post;

public class PostService {
    PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


}
