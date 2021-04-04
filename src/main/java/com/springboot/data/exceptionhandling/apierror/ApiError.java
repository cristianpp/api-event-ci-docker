package com.springboot.data.exceptionhandling.apierror;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String debugMessage;

    private ApiError(){
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status){
        this();
        this.status = status;
    }

    public ApiError(HttpStatus status, Throwable ex){
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();

    }

    public ApiError(HttpStatus status, String message, Throwable ex){
        this();
        this.status = status;
        this. message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiError(HttpStatus status, String message){
        this();
        this.status = status;
        this. message = message;
    }
}