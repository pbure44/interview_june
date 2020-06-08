package com.app.burenok.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder(builderMethodName = "of", buildMethodName = "create")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ExceptionJSONInfo {

    @JsonProperty(value = "httpStatus")
    private HttpStatus httpStatus;

    @JsonProperty(value = "error")
    private String error;

    @JsonProperty(value = "errorMessage")
    private String errorMessage;
}