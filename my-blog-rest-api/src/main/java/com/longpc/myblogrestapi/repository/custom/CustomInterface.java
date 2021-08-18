package com.longpc.myblogrestapi.repository.custom;

import com.longpc.myblogrestapi.entity.AuthenEntity;

public interface CustomInterface  {
    AuthenEntity findByEmail();
}
