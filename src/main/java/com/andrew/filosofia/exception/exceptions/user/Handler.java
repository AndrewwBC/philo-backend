package com.andrew.filosofia.exception.exceptions.user;

import com.andrew.filosofia.user.validations.UserValidateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class Handler {

    @ExceptionHandler
    public ResponseEntity<List<UserValidateResponse>> handleSignInException(SignInException signInException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(signInException.getCustomMessage());
    }


}
