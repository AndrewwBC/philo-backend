package com.andrew.filosofia.user;


import com.andrew.filosofia.user.dto.UserDTO;
import com.andrew.filosofia.user.validations.UserValidate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class UserService {

    @Value("${ADM_MAIL}")
    private String admMail;
    private UserValidate userValidate;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, UserValidate userValidate) {
       this.userValidate = userValidate;
       this.userRepository = userRepository;
    }

    public User createUser(UserDTO userDTO) {
        this.userValidate.signInValidate(userDTO);

        UserRole userRole = this.handleUserRole(userDTO.email());

        User user = User.fromCreateUser(userDTO, userRole);
        return userRepository.save(user);
    }

    public User updateUser(UserDTO userDTO, String id) {
        this.userValidate.updateValidate(userDTO, id);

        UserRole userRole = this.handleUserRole(userDTO.email());

        User user = this.userRepository.findByUsername(userDTO.username()).orElseThrow(() ->
            new NoSuchElementException("Usuário não encontrado"));

        user.updateFromUserDTO(userDTO, userRole);

        return this.userRepository.save(user);
    }

    public User getUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new NoSuchElementException(("Usuário não encontrado.")));

        return user;
    }

    private UserRole handleUserRole(String email){
        return Objects.equals(email, admMail) ? UserRole.ADMIN : UserRole.USER;
    }

}
