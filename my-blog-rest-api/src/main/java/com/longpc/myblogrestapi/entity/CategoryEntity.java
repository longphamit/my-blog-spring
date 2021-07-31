package com.longpc.myblogrestapi.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "category")
public class CategoryEntity {
    @Id
    private String id;
    private String name;
}
