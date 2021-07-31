package com.longpc.myblogrestapi.service;

import com.longpc.myblogrestapi.dto.AuthenDTO;
import com.longpc.myblogrestapi.entity.AuthenEntity;

public interface AuthenService {
    AuthenEntity login(AuthenDTO authenDTO);

    AuthenEntity register(AuthenDTO authenDTO);
}
