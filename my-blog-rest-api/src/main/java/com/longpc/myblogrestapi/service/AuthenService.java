package com.longpc.myblogrestapi.service;

import com.longpc.myblogrestapi.dto.AuthenDTO;
import com.longpc.myblogrestapi.dto.AuthenResponseDTO;
import com.longpc.myblogrestapi.entity.AuthenEntity;

public interface AuthenService {
    AuthenResponseDTO login(AuthenDTO authenDTO);

    AuthenEntity register(AuthenDTO authenDTO);
}
