package com.andrew.filosofia.user.validations;

import com.andrew.filosofia.user.dto.UserDTO;

public interface UserValidations {
    void signInValidate(UserDTO userDTO);
}
