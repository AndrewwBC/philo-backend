package com.andrew.filosofia.user;

import com.andrew.filosofia.user.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(userDTO));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody @Valid )

    @GetMapping
    public ResponseEntity<?> getUser(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(username));
    }

}
