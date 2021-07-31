package com.longpc.myblogrestapi.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BlogDTO implements Serializable {
    private String id;
    private String title;
    private String content;
    private String author;
    private Date createdAt;
    private String categoryId;
}
