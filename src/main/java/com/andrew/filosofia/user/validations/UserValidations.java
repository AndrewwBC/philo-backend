package com.andrew.filosofia.user.validations;

import com.andrew.filosofia.user.DTO.CreateUser;

public interface UserValidations {
    void signInValidate(CreateUser createUser);
}
