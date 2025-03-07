package com.andrew.filosofia.user.validations;

public record UserExceptionsResponse(
        String fieldName,
        String message
)
{
    public static UserExceptionsResponse fromData(String fieldName, String message){
        return new UserExceptionsResponse(fieldName, message);
    }
}
