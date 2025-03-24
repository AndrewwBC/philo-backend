package com.andrew.filosofia.auth;

import com.andrew.filosofia.auth.dto.SignInDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody @Valid SignInDTO signInDTO){
        System.out.println(signInDTO);
        return ResponseEntity.status(HttpStatus.OK).body(this.authService.signIn(signInDTO));
    }

}
