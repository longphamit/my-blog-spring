package com.longpc.myblogrestapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {
    private String id;
    private String name;
}
