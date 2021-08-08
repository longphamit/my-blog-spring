package com.longpc.myblogrestapi.entity;


import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Entity
@Table(name = "refreshtoken")
public class RefreshTokenEntity {
    @Id
    private String id;
    private Instant expiration;
    private String value;
    private String authenId;
}
