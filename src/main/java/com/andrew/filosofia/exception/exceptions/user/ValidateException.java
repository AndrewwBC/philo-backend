package com.andrew.filosofia.exception.exceptions.user;

import com.andrew.filosofia.user.validations.UserExceptionsResponse;
import jakarta.validation.ValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;


@ControllerAdvice
public class ValidateException extends ValidationException {
    private final List<UserExceptionsResponse> customMessage;
    public ValidateException(List<UserExceptionsResponse> customMessage){
        this.customMessage = customMessage;
    }
    public List<UserExceptionsResponse> getCustomMessage(){
        return this.customMessage;
    }
}
