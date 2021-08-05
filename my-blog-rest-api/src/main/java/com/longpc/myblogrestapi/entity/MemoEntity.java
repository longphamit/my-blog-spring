package com.longpc.myblogrestapi.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "memo")
public class MemoEntity {
    @Id
    private String id;
    private Date createdAt;
    private String content;
    private String image;
    private int year;
}
