package com.stockexchange.userservice.controller;


import com.stockexchange.userservice.controller.dto.UserRequest;
import com.stockexchange.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @Value("{spring.datasource.url}")
    private String dbUrl;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
    }
    
    @GetMapping("/test")
    public String test() {
        return dbUrl;
    }
    
//    @GetMapping
//    public ResponseEntity<List<UserResponse>> getAllUsers() {
//        List<UserR> users = userService.getAllUsers();
//        return ResponseEntity.ok(userService.getAllUsers());
//    }
    
    
    
    
}
