package com.andrew.filosofia.user.validations;

import com.andrew.filosofia.exception.exceptions.user.SignInException;
import com.andrew.filosofia.user.dto.UserDTO;
import com.andrew.filosofia.user.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidate implements UserValidations {
    private final UserRepository userRepository;
    public UserValidate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signInValidate(UserDTO userDTO) {
        boolean emailExists = userRepository.existsByEmail(userDTO.email());
        boolean usernameExists = userRepository.existsByUsername(userDTO.username());

        List<UserValidateResponse> userValidateResponse = new ArrayList<>();

        if(emailExists) {
            userValidateResponse.add(
                    UserValidateResponse.fromData("email", "Email already exists."));
        }

        if(usernameExists){
            userValidateResponse.add(
                    UserValidateResponse.fromData("username", "Username already exists."));
        }

        if(!userValidateResponse.isEmpty()) {
            throw new SignInException(userValidateResponse);
        }
    }
}
