package com.bci.ejerciciojava.models.service.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "description",
        "message"
})
@Data
@NoArgsConstructor
public class ApiStatus {

    @JsonProperty("description")
    private HttpStatus status;
    @JsonProperty("message")
    private String message;


    public ApiStatus(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;

    }

}
