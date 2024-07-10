package com.morioucho.samarkand.controller;

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
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(401).body("Invalid username or password.");
    }
}
