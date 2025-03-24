package com.andrew.filosofia.auth;

import com.andrew.filosofia.auth.dto.SignInDTO;
import com.andrew.filosofia.infra.TokenService;
import com.andrew.filosofia.user.User;
import com.andrew.filosofia.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AuthService(AuthenticationManager authManager,TokenService tokenService,UserRepository userRepository) {
        this.authManager = authManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }
    public String signIn(SignInDTO signInDTO) {
        var usernamePass = new UsernamePasswordAuthenticationToken(signInDTO.email(), signInDTO.password());
        System.out.println(usernamePass);
        var auth = this.authManager.authenticate(usernamePass);
        System.out.println(auth);
        return this.tokenService.generateToken((User) auth.getPrincipal());
    }

}
