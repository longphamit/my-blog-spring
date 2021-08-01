package com.longpc.myblogrestapi.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "contact")
public class ContactEntity {
    @Id
    private String id;
    private String email;
    private String content;
    private Date createAt;
}
