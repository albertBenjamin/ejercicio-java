package com.bci.ejerciciojava.models.service;

import com.bci.ejerciciojava.models.dao.PhoneDao;
import com.bci.ejerciciojava.models.dao.UsuarioDao;
import com.bci.ejerciciojava.models.entity.Phone;
import com.bci.ejerciciojava.models.entity.User;
import com.bci.ejerciciojava.models.service.configuration.ApiStatus;
import com.bci.ejerciciojava.models.service.dto.UserRequest;
import com.bci.ejerciciojava.models.service.dto.UserResponse;
import com.bci.ejerciciojava.util.UtilRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

@Slf4j
@Service
public class UsuarioServiceImpl implements  IUsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private PhoneDao phoneDao;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public UserResponse findByEmail(String email) throws JsonProcessingException {
        return objectMapper.readValue(objectMapper.writeValueAsString(usuarioDao.findByEmail(email)), UserResponse.class);
    }

    @Override
    @Transactional
    public UserResponse save(UserRequest userRequest) throws JsonProcessingException {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserResponse  userResponse =  new UserResponse();
        ApiStatus apiStatus = new ApiStatus();
        User user = objectMapper.readValue(objectMapper.writeValueAsString(userRequest), User.class);
        for(Phone element : user.getPhones()){
            phoneDao.save(element);
        }
        userResponse = objectMapper.readValue(objectMapper.writeValueAsString(usuarioDao.save(user)), UserResponse.class);
        return userResponse;
    }


}
