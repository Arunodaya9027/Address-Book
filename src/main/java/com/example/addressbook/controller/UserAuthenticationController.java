package com.example.addressbook.controller;

import com.example.addressbook.interfaces.IUserAuthenticationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.addressbook.dto.*;
import com.example.addressbook.exception.UserException;
import com.example.addressbook.dto.UserAuthenticationDTO;

/**
 * Controller for User Authentication operations.
 * Provides endpoints to manage user authentication data.
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")    // Base URL for User Authentication API
public class UserAuthenticationController {

    // UserAuthenticationService instance to perform CRUD operations on user authentication data.
    @Autowired
    IUserAuthenticationService userAuthenticationService;

    /**
     * Endpoint to register a new user.
     * @param userDTO - The UserAuthenticationDTO object containing user details.
     * @return ResponseEntity with created UserAuthenticationDTO
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<?>> register(@Valid @RequestBody UserAuthenticationDTO userDTO) {
        log.info("Registering user with email: {}", userDTO.getEmail());
        try {
            UserAuthenticationDTO user = userAuthenticationService.register(userDTO);
            ResponseDTO<UserAuthenticationDTO> responseUserDTO = new ResponseDTO<UserAuthenticationDTO>("User details is submitted!", user);
            return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error registering user: {}", e.getMessage());
            ResponseDTO<String> responseUserDTO = new ResponseDTO<String>("User details is not submitted!", e.getMessage());
            return new ResponseEntity<>(responseUserDTO, HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Endpoint to login a user.
     * @param loginDTO - The LoginDTO object containing login details.
     * @return ResponseEntity with JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<?>> login(@Valid @RequestBody LoginDTO loginDTO) {
        log.info("Logging in user with email: {}", loginDTO.getEmail());
        try {
            String result = userAuthenticationService.login(loginDTO);
            ResponseDTO<String> responseUserDTO = new ResponseDTO<String>("Login successfully!!", result);
            return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
        } catch (UserException e) {
            log.error("Error logging in user: {}", e.getMessage());
            ResponseDTO<String> responseUserDTO = new ResponseDTO<String>("Login failed!!", e.getMessage());
            return new ResponseEntity<>(responseUserDTO, HttpStatus.BAD_REQUEST);
        }
    }
}
