package com.bci.ejerciciojava.models.service;


import com.bci.ejerciciojava.models.service.dto.UserRequest;
import com.bci.ejerciciojava.models.service.dto.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;



public interface IUsuarioService {

    public UserResponse findByEmail(String email) throws JsonProcessingException;
    public UserResponse save(UserRequest user) throws JsonProcessingException;

}
