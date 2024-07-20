package com.morioucho.samarkand.controller;

import com.morioucho.samarkand.dto.UserRegistrationDTO;
import com.morioucho.samarkand.model.Session;
import com.morioucho.samarkand.model.User;
import com.morioucho.samarkand.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param user - A user with the fields "username" and "password".
     * @return Returns the user if the request is passed and error code "401" if an invalid username or password is given.
     * @author Galagyy
     *
     * Endpoint: "/api/users/login"
     * Method: "POST"
     * Content-Type: "application/json"
     * Request Content: "username" and "password"
     *
     * This endpoint allows users to log in by verifying their username and password.
     * If the credentials are correct, the server responds with the user details.
     * If the credentials are incorrect, the server responds with an error message (401).
     */

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        User foundUser = userService.findByUsername(user.getUsername());

        if(foundUser != null && foundUser.getUsername().equals(user.getUsername()) && foundUser.getPassword().equals(user.getPassword())){
            Session newSession = Session.generateToken();
            foundUser.setSession(newSession);

            return ResponseEntity.ok(newSession.getToken());
        }

        return ResponseEntity.status(401).body("Invalid username or password.");
    }

    /**
     * @param userDTO - A user DTO that passes information related to creating a new user.
     * @return Returns the user if the request is passed and error code "400" if the username is already taken.
     * @author Galagyy
     *
     * Endpoint: "/api/users/register"
     * Method: "POST"
     * Content-Type: "application/json"
     * Request Content: "username" and "password"
     *
     * This endpoint allows users to create a new account with a username and password.
     * If the username already exists, the system returns an error with status 400.
     * If the username doesn't exist, the system creates a new user and passes it back.
     */

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationDTO userDTO){
        if(usernameExists(userDTO.getUsername())){
            return ResponseEntity.status(400).body("The username you chose has already been taken.");
        }

        if(!passwordCheck(userDTO.getUsername(), userDTO.getPassword())){
            return ResponseEntity.status(400).body("Your password cannot be your username and must be greater than 8 characters in length.");
        }

        User newUser = userService.registerUser(userDTO);

        return ResponseEntity.ok(newUser);
    }

    private boolean passwordCheck(String username, String password){
        boolean accepted = password.length() >= 8;

        if(password.contains(username)){
            accepted = false;
        }

        return accepted;
    }

    private boolean usernameExists(String username){
        return userService.findByUsername(username) != null;
    }
}
