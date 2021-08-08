package com.longpc.myblogrestapi.service;

import com.longpc.myblogrestapi.entity.RefreshTokenEntity;

public interface RefreshTokenService {
    RefreshTokenEntity createRefreshToken(String userId);
    public String reNewToken(String refreshToken);
}
