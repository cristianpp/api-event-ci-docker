package com.springboot.data.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class EventDTO implements Serializable {

    private Long id;

    @JsonProperty(required = true)
    @NotBlank
    @NotEmpty
    @Size(min = 5, max=200)
    private String name;

    @JsonProperty(required = true)
    @Positive
    private Double value;
}
