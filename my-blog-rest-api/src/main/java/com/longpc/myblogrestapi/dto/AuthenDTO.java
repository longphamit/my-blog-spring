package com.longpc.myblogrestapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class AuthenDTO implements Serializable {
    private String id;
    @NotBlank(message = "email not empty")
    private String email;
    private String password;
}
