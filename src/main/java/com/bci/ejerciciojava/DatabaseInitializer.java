package com.bci.ejerciciojava;

import com.bci.ejerciciojava.models.dao.PhoneDao;
import com.bci.ejerciciojava.models.dao.RoleDao;
import com.bci.ejerciciojava.models.dao.UsuarioDao;
import com.bci.ejerciciojava.models.entity.Phone;
import com.bci.ejerciciojava.models.entity.Role;
import com.bci.ejerciciojava.models.entity.User;
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

import java.util.ArrayList;
import java.util.List;

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

            List<String> usernames = new ArrayList();

            usernames.add("ada.lovelace@nix.io");
            usernames.add("alan.turing@nix.io");
            usernames.add("dennis.ritchie@nix.io");

            List<String> roles = new ArrayList();

            roles.add("USER_ADMIN");
            roles.add("AUTHOR_ADMIN");
            roles.add("BOOK_ADMIN");

            /*for (int i = 0; i < roles.size(); ++i) {
                Role role = new Role();
                role.setAuthority(roles.get(i));
                roleDao.save(role);
            }*/

            for (int i = 0; i < usernames.size(); ++i) {

                UserRequest userRequest = new UserRequest();
                userRequest.setPhones(new ArrayList<>());
                userRequest.setRole(new ArrayList<>());
                PhoneRequest phoneRequest = new PhoneRequest();
                phoneRequest.setNumberPhone(number.get(i));
                phoneRequest.setCityCode("1");
                phoneRequest.setCountryCode("57");
                userRequest.setName(usernames.get(i));
                userRequest.setEmail(usernames.get(i));
                userRequest.getPhones().add(phoneRequest);
                userRequest.getRole().add(roles.get(i));
                userRequest.setPassword(passwordEncoder.encode(password));
                usuarioDao.create(userRequest);
            }
        }

}
