package com.bci.ejerciciojava.models.service.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"number",
"cityCode",
"contrycode"
})
@ToString
public class PhoneRequest {
    @JsonProperty("numberPhone")
    private String numberPhone;
    @JsonProperty("cityCode")
    private String cityCode;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("number")
    public String getNumberPhone() {
        return numberPhone;
    }
    @JsonProperty("number")
    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
    @JsonProperty("cityCode")
    public String getCityCode() {
        return cityCode;
    }
    @JsonProperty("cityCode")
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    @JsonProperty("contrycode")
    public String getCountryCode() {
        return countryCode;
    }
    @JsonProperty("contrycode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

