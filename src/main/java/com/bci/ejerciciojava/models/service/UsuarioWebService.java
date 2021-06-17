package com.bci.ejerciciojava.models.service;

import com.bci.ejerciciojava.models.dao.PhoneDao;
import com.bci.ejerciciojava.models.dao.RoleDao;
import com.bci.ejerciciojava.models.dao.UsuarioDao;
import com.bci.ejerciciojava.models.entity.Role;
import com.bci.ejerciciojava.models.entity.User;
import com.bci.ejerciciojava.models.service.dto.UserRequest;
import com.bci.ejerciciojava.models.service.dto.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;

@Service
public class UsuarioWebService implements UserDetailsService {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PhoneDao phoneDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioDao
                .findByName(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(format("User with username - %s, not found", username))
                );
    }

    @Transactional
    public UserResponse create(UserRequest userRequest) throws JsonProcessingException {
        User user = objectMapper.readValue(objectMapper.writeValueAsString(userRequest), User.class);
        user.setAuthorities(roleDao.findByAuthority(userRequest.getRole().iterator().next()));
        phoneDao.save(user.getPhones().iterator().next());
        UserResponse response = objectMapper.readValue(objectMapper.writeValueAsString(usuarioDao.save(user)), UserResponse.class);
        return response;
    }




}
