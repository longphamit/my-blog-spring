package com.longpc.myblogrestapi.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "authen")
public class AuthenEntity {
    @Id
    private String id;
    private String email;
    private String password;
    private String avatar;
    private String phone;
    private String github;
    private String linkedln;
    private String facebook;
    private String gitlab;
}
