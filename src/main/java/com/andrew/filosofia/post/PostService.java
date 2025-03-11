package com.andrew.filosofia.post;

import com.andrew.filosofia.post.dto.PostDTO;
import com.andrew.filosofia.user.User;
import com.andrew.filosofia.user.UserRepository;

import java.util.NoSuchElementException;

public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPost(PostDTO postDTO){
        User user = this.userRepository.findById(postDTO.userId()).orElseThrow(()
                -> new NoSuchElementException("User not found"));

        return Post.fromDTO(postDTO, user);
    }

}
