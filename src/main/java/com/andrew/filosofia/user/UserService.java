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

    public User createUser(UserDTO userDTO){
        this.userValidate.signInValidate(userDTO);

        UserRole userRole;
        if (Objects.equals(userDTO.email(),admMail)) {
            userRole = UserRole.ADMIN;
        } else {
            userRole = UserRole.USER;
        }

        User user = User.fromCreateUser(userDTO, userRole);
        return userRepository.save(user);
    }

    public User getUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new NoSuchElementException(("Usuário não encontrado.")));

        return user;
    }

}
