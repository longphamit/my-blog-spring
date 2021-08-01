package com.longpc.myblogrestapi.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Data
public class RefreshTokenEntity {
    private String id;
    private Instant expiration;
    private String value;
    private String email;
}
