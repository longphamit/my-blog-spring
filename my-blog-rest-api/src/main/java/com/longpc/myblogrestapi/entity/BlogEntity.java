package com.longpc.myblogrestapi.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "blog")
public class BlogEntity {
    @Id
    private String id;
    private String title;
    private String content;
    private String author;
    private Date createdAt;
    private String categoryId;
    private String imageShow;
}
