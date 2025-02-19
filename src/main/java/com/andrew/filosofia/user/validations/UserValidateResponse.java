package com.andrew.filosofia.user.validations;

public record UserValidateResponse(
        String fieldName,
        String message
)
{
    public static UserValidateResponse fromData(String fieldName, String message){
        return new UserValidateResponse(fieldName, message);
    }
}
