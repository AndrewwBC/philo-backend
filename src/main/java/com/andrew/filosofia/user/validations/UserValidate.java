package com.andrew.filosofia.user.validations;

import com.andrew.filosofia.exception.exceptions.user.ValidateException;
import com.andrew.filosofia.user.User;
import com.andrew.filosofia.user.dto.UserDTO;
import com.andrew.filosofia.user.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Component
public class UserValidate implements UserValidations {
    private final UserRepository userRepository;
    List<UserValidateResponse> userValidateResponse = new ArrayList<>();
    public UserValidate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signInValidate(UserDTO userDTO) {
        boolean emailExists = userRepository.existsByEmail(userDTO.email());
        boolean usernameExists = userRepository.existsByUsername(userDTO.username());

        if(emailExists) {
            this.handleUserValidateResponse("email", "Email already exists123.");
        }

        if(usernameExists){
           this.handleUserValidateResponse("username", "Username already exists.");
        }

        if(!this.userValidateResponse.isEmpty()) {
            throw new ValidateException(this.userValidateResponse);
        }
    }

    @Override
    public void updateValidate(UserDTO userDTO, String id) {

        User user = this.userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Usuário não encontrado!"));

        String oldUsername = user.getUsername();
        String oldEmail = user.getEmail();

        String newUsername = userDTO.username();
        String newEmail = userDTO.email();

        if(!Objects.equals(oldUsername, newUsername)) {
            boolean newUsernameAlreadyExists = this.userRepository.existsByUsername(newUsername);
        }

        if(!Objects.equals(oldEmail, newEmail)) {
            boolean newEmailAlreadyExists = this.userRepository.existsByEmail(newEmail);
        }

    }

    private void handleUserValidateResponse(String fieldName, String message){
        this.userValidateResponse.add(UserValidateResponse.fromData(fieldName, message));
    }

}
