package com.bci.ejerciciojava.controllers;

import com.bci.ejerciciojava.models.service.IUsuarioService;
import com.bci.ejerciciojava.models.service.configuration.ApiError;
import com.bci.ejerciciojava.models.service.dto.UserRequest;
import com.bci.ejerciciojava.models.service.dto.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;


@Slf4j
@RestController
@RequestMapping("/api/usuario")
@RolesAllowed("USER_ADMIN")
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
            ApiError apiError =  new ApiError(HttpStatus.BAD_REQUEST,"el correo del usuario ya existe");
            isCreated.setApiError(apiError);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(isCreated);
        }else{
            isCreated = iUsuarioService.save(user);
            ApiError apiSuccess =  new ApiError(HttpStatus.OK,"Creación usuario exitosa");
            isCreated.setApiError(apiSuccess);
        }
        log.info("saveUser | save | Response | {}",gson.toJson(isCreated));
        return ResponseEntity.status(HttpStatus.OK).body(isCreated);
    }

    @PatchMapping
    public ResponseEntity<UserResponse> patchUser(@RequestBody UserRequest user) throws JsonProcessingException {
        log.info("REST request to patch user : {}", user);
        UserResponse userResponse = iUsuarioService.save(user);
        log.info("REST response from patch user: {}", userResponse.toString());
        return ResponseEntity.accepted().body(userResponse);
    }
}
