package com.andrew.filosofia.auth;

import com.andrew.filosofia.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
