package com.andrew.filosofia.auth;

import com.andrew.filosofia.auth.dto.SignInDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody @Valid SignInDTO signInDTO){
        return ResponseEntity.ok(1);
    }

}
