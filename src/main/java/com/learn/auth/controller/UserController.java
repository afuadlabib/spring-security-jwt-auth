package com.learn.auth.controller;

import com.learn.auth.dto.UserDto;
import com.learn.auth.entity.User;
import com.learn.auth.security.JwtService;
import com.learn.auth.service.UserService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final JwtService jwtService;
    private final UserService userService;


    public UserController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto){
//        System.out.println(userService.register(userDto));
        Integer id = (Integer) jwtService.getClaims(jwtService.generateToken(User.builder().id(1L).username("User").build())).get("userId");
        return ResponseEntity.ok().body(id);
    }
    @GetMapping
    public String welcome(){
        return "Welcome";
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto){
//
        String message = "login";
        return ResponseEntity.ok().body(userService.login(userDto));
    }
}
