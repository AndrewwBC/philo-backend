package com.andrew.filosofia.user;


import com.andrew.filosofia.user.DTO.CreateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class UserService {

    @Value("${ADM_MAIL}")
    private String admMail;

    @Autowired
    UserRepository userRepository;

    public User createUser(CreateUser createUser){

        UserRole userRole;
        if (Objects.equals(createUser.email(),admMail)) {
            userRole = UserRole.ADMIN;
        } else {
            userRole = UserRole.USER;
        }

        User user = User.fromCreateUser(createUser, userRole);
        return userRepository.save(user);
    }

    public User getUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new NoSuchElementException(("Usuário não encontrado.")));

        return user;
    }

}
