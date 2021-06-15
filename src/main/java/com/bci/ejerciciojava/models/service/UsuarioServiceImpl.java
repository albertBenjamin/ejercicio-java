package com.bci.ejerciciojava.models.service;

import com.bci.ejerciciojava.models.dao.PhoneDao;
import com.bci.ejerciciojava.models.dao.UsuarioDao;
import com.bci.ejerciciojava.models.entity.User;
import com.bci.ejerciciojava.models.service.dto.UserRequest;
import com.bci.ejerciciojava.models.service.dto.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements  IUsuarioService{

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private PhoneDao phoneDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional(readOnly = true)
    public UserResponse findByEmail(String email) throws JsonProcessingException {
        return objectMapper.readValue(objectMapper.writeValueAsString(usuarioDao.findByEmail(email)), UserResponse.class);
    }

    @Override
    public UserResponse save(UserRequest userRequest) throws JsonProcessingException {
        User user = objectMapper.readValue(objectMapper.writeValueAsString(userRequest), User.class);
        phoneDao.save(user.getPhones().get(0));
        return objectMapper.readValue(objectMapper.writeValueAsString(usuarioDao.save(user)), UserResponse.class);
    }
}
