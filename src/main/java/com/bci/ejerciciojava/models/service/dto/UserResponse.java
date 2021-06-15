package com.bci.ejerciciojava.models.service.dto;

import com.bci.ejerciciojava.models.entity.Phone;
import com.fasterxml.jackson.annotation.*;
import lombok.ToString;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "email",
        "password",
        "phones",
        "created",
        "modify",
        "last_login",
        "token",
        "isactive"
})
@ToString
public class UserResponse {
    @JsonProperty("id")
    private String idUser;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("phones")
    private List<PhoneResponse> phones;
    @JsonProperty("created")
    private String createAt;
    @JsonProperty("modify")
    private String modifyAt;
    @JsonProperty("last_login")
    private String lastLogin;
    @JsonProperty("token")
    private String token;
    @JsonProperty("isactive")
    private String isActive;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }
    @JsonProperty("password")
    public String getPassword() {
        return password;
    }
    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }
    @JsonProperty("phones")
    public List<PhoneResponse> getPhones() {
        return phones;
    }
    @JsonProperty("phones")
    public void setPhones(List<PhoneResponse> phones) {
        this.phones = phones;
    }
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
    @JsonAnySetter
    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
