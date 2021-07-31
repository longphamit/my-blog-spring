package com.longpc.myblogrestapi.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class AuthenDTO implements Serializable {
    private String id;
    private String email;
    private String password;
}
