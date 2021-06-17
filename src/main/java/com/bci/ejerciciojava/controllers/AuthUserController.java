package com.bci.ejerciciojava.controllers;


import com.bci.ejerciciojava.models.entity.User;
import com.bci.ejerciciojava.config.JwtTokenUtil;
import com.bci.ejerciciojava.models.service.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import com.bci.ejerciciojava.models.service.IUsuarioService;


@RestController
@RequestMapping(path ="/api/public")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    private  JwtTokenUtil jwtTokenUtil;
    @Autowired
    private  IUsuarioService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            User user = (User) authenticate.getPrincipal();
            UserResponse response = objectMapper.readValue(objectMapper.writeValueAsString(user), UserResponse.class);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                    .body(response);
        } catch (BadCredentialsException | JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public UserResponse register(@RequestBody @Valid UserRequest request) throws JsonProcessingException {
        return userService.save(request);
    }
}
