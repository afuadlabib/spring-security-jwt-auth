package com.learn.auth.controller;

import com.learn.auth.dto.AuthDto;
import com.learn.auth.dto.ResponseDto;
import com.learn.auth.dto.UserDto;
import com.learn.auth.entity.User;
import com.learn.auth.security.JwtService;
import com.learn.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto){
        return ResponseEntity.status(201)
                .body(ResponseDto.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .data(userService.register(userDto))
                        .build()
        );
    }
    @GetMapping
    public String welcome(){
        return "Welcome";
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){
        return ResponseEntity.status(200)
                .body(ResponseDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .data(userService.login(userDto))
                        .build()
                );
    }
}
