package com.andrew.filosofia.exception.exceptions.user;

import com.andrew.filosofia.user.validations.UserValidateResponse;
import jakarta.validation.ValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;


@ControllerAdvice
public class SignInException extends ValidationException {
    private final List<UserValidateResponse> customMessage;

    public SignInException(List<UserValidateResponse> customMessage){
        this.customMessage = customMessage;
    }
    public List<UserValidateResponse> getCustomMessage(){
        return this.customMessage;
    }
}
