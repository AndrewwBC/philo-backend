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
    List<UserExceptionsResponse> userValidateExceptions = new ArrayList<>();
    public UserValidate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signInValidate(UserDTO userDTO) {
        this.userValidateExceptions.clear();

        boolean emailExists = userRepository.existsByEmail(userDTO.email());
        boolean usernameExists = userRepository.existsByUsername(userDTO.username());

        if(emailExists) {
            this.addExceptionMessageInUserValidateExceptionList("email");
        }

        if(usernameExists){
           this.addExceptionMessageInUserValidateExceptionList("username");
        }

        this.throwErrorIfListIsNotEmpty();
    }

    @Override
    public void updateValidate(UserDTO userDTO, String id) {
        this.userValidateExceptions.clear();

        User user = this.userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("User not found!"));

        String oldUsername = user.getUsername();
        String oldEmail = user.getEmail();

        String newUsername = userDTO.username();
        String newEmail = userDTO.email();

        if(!Objects.equals(oldUsername, newUsername)) {
            boolean newUsernameAlreadyExists = this.userRepository.existsByUsername(newUsername);
            if(newUsernameAlreadyExists){
                this.addExceptionMessageInUserValidateExceptionList("username");
            }
        }

        if(!Objects.equals(oldEmail, newEmail)) {
            boolean newEmailAlreadyExists = this.userRepository.existsByEmail(newEmail);
            if(newEmailAlreadyExists){
                this.addExceptionMessageInUserValidateExceptionList("email");
            }
        }

        this.throwErrorIfListIsNotEmpty();
    }

    private void addExceptionMessageInUserValidateExceptionList(String fieldName){
        String message = Objects.equals(fieldName, "username") ? "Username already exists!" : "Email already exists";
        this.userValidateExceptions.add(UserExceptionsResponse.fromData(fieldName, message));
    }

    private void throwErrorIfListIsNotEmpty(){
        if(!this.userValidateExceptions.isEmpty()) {
            throw new ValidateException(this.userValidateExceptions);
        }
    }

}
