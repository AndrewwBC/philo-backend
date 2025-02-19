package com.andrew.filosofia.user.validations;

import com.andrew.filosofia.user.DTO.CreateUser;
import com.andrew.filosofia.user.UserRepository;

import java.util.List;

public class UserValidate implements UserValidations {
    private final UserRepository userRepository;

    private List<UserValidateResponse> userValidateResponse;

    public UserValidate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signInValidate(CreateUser createUser) {
        boolean emailExists = userRepository.existsByEmail(createUser.email());
        boolean usernameExists = userRepository.existsByUsername(createUser.username());

        if(emailExists) {
            this.userValidateResponse.add(
                    UserValidateResponse.fromData("email", "Email already exists."));
        }

        if(usernameExists){
            this.userValidateResponse.add(
                    UserValidateResponse.fromData("username", "Username already exists."));
        }

    }
}
