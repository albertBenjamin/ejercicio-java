package com.bci.ejerciciojava.controllers;

import com.bci.ejerciciojava.models.entity.User;
import com.bci.ejerciciojava.models.service.IUsuarioService;
import com.bci.ejerciciojava.models.service.dto.UserRequest;
import com.bci.ejerciciojava.models.service.dto.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService iUsuarioService;

    protected static Gson gson = new Gson();

    @PostMapping("/nuevo")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest user) throws JsonProcessingException {
        log.info("REST request to saver User : {}", user);
        HttpStatus httpStatus;
        UserResponse isCreated = iUsuarioService.findByEmail(user.getEmail());
        if (isCreated != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }else{
            isCreated = iUsuarioService.save(user);
            httpStatus = HttpStatus.OK;
        }
        log.info("saveUser | save | Response | {}",gson.toJson(isCreated));
        return ResponseEntity.status(httpStatus).body(isCreated);
    }

    @PatchMapping
    public ResponseEntity<UserResponse> patchUser(@RequestBody UserRequest user) throws JsonProcessingException {
        log.info("REST request to patch user : {}", user);
        UserResponse userResponse = iUsuarioService.save(user);
        log.info("REST response from patch user: {}", userResponse.toString());
        return ResponseEntity.accepted().body(userResponse);
    }
}
