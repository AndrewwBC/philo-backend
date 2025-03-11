package com.andrew.filosofia.post;

import com.andrew.filosofia.post.dto.PostDTO;
import com.andrew.filosofia.user.User;
import com.andrew.filosofia.user.UserRepository;

public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }



}
