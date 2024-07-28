package com.travelbnb.travelbnb.controller;

import com.travelbnb.travelbnb.payload.AppUserDto;
import com.travelbnb.travelbnb.payload.JWTTokenDto;
import com.travelbnb.travelbnb.payload.LoginDto;
import com.travelbnb.travelbnb.repository.AppUserRepository;
import com.travelbnb.travelbnb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService, AppUserRepository appUserRepository) {
        this.userService = userService;
        this.appUserRepository = appUserRepository;
    }

    private AppUserRepository appUserRepository;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(
            @RequestBody AppUserDto appUserDto
    ){
        if (appUserRepository.existsByEmail(appUserDto.getEmail())){
            return new ResponseEntity<>("Email Exists", HttpStatus.BAD_REQUEST);
        }

        if (appUserRepository.existsByUsername(appUserDto.getUsername())){
            return new ResponseEntity<>("User name exists", HttpStatus.BAD_REQUEST);
        }

       // appUserDto.setPassword(BCrypt.hashpw(appUserDto.getPassword(), BCrypt.gensalt(10)));

        AppUserDto user = userService.createUser(appUserDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> verifyLogin(@RequestBody LoginDto loginDto) {
        String token = userService.verifyLogin(loginDto);
        if (token != null) {
            JWTTokenDto jwtTokenDto= new JWTTokenDto();
            jwtTokenDto.setType("JWT Token");
            jwtTokenDto.setToken(token);
            return new ResponseEntity<>(jwtTokenDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("invalid token", HttpStatus.OK);
        }
    }
}
