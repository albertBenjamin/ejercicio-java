package com.bci.ejerciciojava;

import com.bci.ejerciciojava.models.dao.PhoneDao;
import com.bci.ejerciciojava.models.dao.RoleDao;
import com.bci.ejerciciojava.models.entity.Role;
import com.bci.ejerciciojava.models.service.UsuarioWebService;
import com.bci.ejerciciojava.models.service.dto.PhoneRequest;
import com.bci.ejerciciojava.models.service.dto.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final String password = "Test12345_";

    @Autowired
    private UsuarioWebService usuarioDao;


    @Autowired
    private PhoneDao phoneDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

            List<String> number = new ArrayList();

            number.add("1234567");
            number.add("12345678");
            number.add("123456798");

            List<String> emails = new ArrayList();
            emails.add("ada.lovelace@nix.io");
            emails.add("alan.turing@nix.io");
            emails.add("dennis.ritchie@nix.io");

            List<String> names = new ArrayList();
            names.add("cristian");
            names.add("gonzalo");
            names.add("jorge");

            Role role = new Role();
            role.setAuthority("USER_ADMIN");
            roleDao.save(role);

            for (int i = 0; i < names.size(); ++i) {
                UserRequest userRequest = new UserRequest();
                userRequest.setPhones(new ArrayList<>());
                PhoneRequest phoneRequest = new PhoneRequest();
                phoneRequest.setNumberPhone(number.get(i));
                phoneRequest.setCityCode("1");
                phoneRequest.setCountryCode("57");
                userRequest.setName(names.get(i));
                userRequest.setEmail(emails.get(i));
                userRequest.getPhones().add(phoneRequest);
                userRequest.setRole(new HashSet<>(Arrays.asList("USER_ADMIN")));
                userRequest.setPassword(passwordEncoder.encode(password));
                usuarioDao.upsert(userRequest);
            }
        }

}
