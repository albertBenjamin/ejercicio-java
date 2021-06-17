package com.bci.ejerciciojava.controllers;

import com.bci.ejerciciojava.models.service.IUsuarioService;
import com.bci.ejerciciojava.models.service.configuration.ApiStatus;
import com.bci.ejerciciojava.models.service.dto.UserRequest;
import com.bci.ejerciciojava.models.service.dto.UserResponse;
import com.bci.ejerciciojava.util.UtilRequest;
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
        UserResponse response= UtilRequest.validateRequest(user);
        if(!response.getApiStatus().getStatus().equals(HttpStatus.BAD_REQUEST)) {
            response = iUsuarioService.findByEmail(user.getEmail());
            if (response != null) {
                ApiStatus apiError = new ApiStatus(HttpStatus.BAD_REQUEST, "el correo del usuario ya existe");
                response.setApiStatus(apiError);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            } else {
                response = iUsuarioService.save(user);
                ApiStatus apiSuccess = new ApiStatus(HttpStatus.OK, "Creación usuario exitosa");
                response.setApiStatus(apiSuccess);
            }
        }
        log.info("saveUser | save | Response | {}",gson.toJson(response));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping
    public ResponseEntity<UserResponse> patchUser(@RequestBody UserRequest user) throws JsonProcessingException {
        log.info("REST request to patch user : {}", user);
        UserResponse userResponse = iUsuarioService.save(user);
        log.info("REST response from patch user: {}", userResponse.toString());
        return ResponseEntity.accepted().body(userResponse);
    }
}
