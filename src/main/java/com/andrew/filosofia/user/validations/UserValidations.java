package com.andrew.filosofia.user.validations;

public interface UserValidations {
    void usernameAlreadyExists(String username);
    void emailAlreadyExists(String email);
}
