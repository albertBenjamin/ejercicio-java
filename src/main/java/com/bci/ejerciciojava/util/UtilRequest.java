package com.bci.ejerciciojava.util;

import com.bci.ejerciciojava.models.service.configuration.ApiStatus;
import com.bci.ejerciciojava.models.service.dto.UserRequest;
import com.bci.ejerciciojava.models.service.dto.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilRequest {

    public static final Pattern VALID_EMAIL =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", Pattern.CASE_INSENSITIVE);



    public static UserResponse validateRequest(UserRequest userRequest) throws JsonProcessingException {
        UserResponse userResponse = new UserResponse();
        ApiStatus apiNull= new ApiStatus(HttpStatus.OK, "OK");
        Matcher matcherEmail = VALID_EMAIL.matcher(userRequest.getEmail());
        Matcher matcherPassword = VALID_PASSWORD.matcher(userRequest.getPassword());
        if (!matcherEmail.matches()) {
            apiNull.setStatus(HttpStatus.BAD_REQUEST);
            apiNull.setMessage("formato correo invalido");
        }
        if (!matcherPassword.matches()) {
            apiNull.setStatus(HttpStatus.BAD_REQUEST);
            apiNull.setMessage("formato password invalido");
        }
        userResponse.setApiStatus(apiNull);
        return userResponse;
    }
}
