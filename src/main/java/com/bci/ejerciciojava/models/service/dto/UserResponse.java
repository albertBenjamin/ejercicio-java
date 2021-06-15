package com.bci.ejerciciojava.models.service.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "email",
        "password",
        "phones",
        "createAt",
        "modifyAt",
        "lastLogin",
        "token",
        "isActive"
})
@Getter
@Setter
@ToString
public class UserResponse {
    @JsonProperty("idUser")
    private String idUser;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("phones")
    private List<PhoneResponse> phones;
    @JsonProperty("createAt")
    private String createAt;
    @JsonProperty("modifyAt")
    private String modifyAt;
    @JsonProperty("lastLogin")
    private String lastLogin;
    @JsonProperty("token")
    private String token;
    @JsonProperty("isActive")
    private String isActive;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
    @JsonAnySetter
    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
