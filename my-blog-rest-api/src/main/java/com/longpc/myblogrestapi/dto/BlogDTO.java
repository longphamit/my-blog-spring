package com.longpc.myblogrestapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class BlogDTO implements Serializable {
    private String id;
    @NotBlank(message = "Title must not empty")
    private String title;
    @NotBlank(message = "Content must not empty")
    private String content;
    private String author;
    private Date createdAt;
    @NotBlank(message = "CategoryId must not empty")
    private String categoryId;
}
