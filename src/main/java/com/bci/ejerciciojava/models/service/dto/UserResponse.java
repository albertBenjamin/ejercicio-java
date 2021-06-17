package com.bci.ejerciciojava.models.service.dto;

import com.bci.ejerciciojava.models.service.configuration.ApiStatus;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
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
        "isActive",
        "Status"
})
@Data
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
    @JsonProperty("Status")
    private ApiStatus apiStatus;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
    @JsonAnySetter
    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
