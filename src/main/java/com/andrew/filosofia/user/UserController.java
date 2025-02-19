package com.andrew.filosofia.user;


import com.andrew.filosofia.user.DTO.CreateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUser createUser){
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(createUser));
    }

    @GetMapping
    public ResponseEntity<?> getUser(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(username));
    }

}
