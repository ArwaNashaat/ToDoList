package com.example.controllers;

import com.example.entities.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping (path = "/ToDo/RegisterUser")
    public ResponseEntity registerUser(@RequestBody User user){
        boolean isValidEmail = validateEmail(user.getEmail());
        boolean isValidPassword = validatePassword(user.getPassword());
        if(isValidEmail && isValidPassword) {
            String encodedPassword = encodePassword(user.getPassword());
            user.setPassword(encodedPassword);
            return new ResponseEntity(userRepository.save(user), HttpStatus.OK);
        }
        return new ResponseEntity("Wrong Email or Password", HttpStatus.BAD_REQUEST);
    }

    private boolean validateEmail(String email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return Pattern.matches(regex, email);
    }

    private boolean validatePassword(String password){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
        return Pattern.matches(regex, password);
    }

    private String encodePassword(String password){
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
        return passwordEncoder.encode(password);
    }

}
