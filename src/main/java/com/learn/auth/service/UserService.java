package com.learn.auth.service;

import com.learn.auth.dto.AuthDto;
import com.learn.auth.dto.UserDto;
import com.learn.auth.entity.User;
import com.learn.auth.repository.UserRepository;
import com.learn.auth.security.JwtService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthDto register(UserDto userDto){
        var user = userRepository.save(User.builder().username(userDto.getUsername()).password(passwordEncoder.encode(userDto.getPassword())).build());
        return AuthDto.builder()
                .token(jwtService.generateToken(user))
                .data(user)
                .build();
    }

    public AuthDto login(UserDto userDto) throws UsernameNotFoundException{
        User user =  userRepository.findByUsername(userDto.getUsername()).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        if(passwordEncoder.matches( userDto.getPassword(), user.getPassword())){
            return AuthDto.builder()
                    .token(jwtService.generateToken(user))
                    .data(user)
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid password or username");
        }
    }
}
