package com.longpc.myblogrestapi.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "image")
public class ImageEntity implements Serializable {
    @Id
    private String id;
    private String path;
    private String blogId;
}
