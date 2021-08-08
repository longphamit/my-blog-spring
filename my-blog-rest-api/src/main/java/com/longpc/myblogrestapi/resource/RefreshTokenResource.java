package com.longpc.myblogrestapi.resource;

import com.longpc.myblogrestapi.dto.RefreshTokenRequestDTO;
import com.longpc.myblogrestapi.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/refresh-token")
public class RefreshTokenResource {
    @Autowired
    private RefreshTokenService refreshTokenService;
    @PostMapping
    public ResponseEntity getTokenFromRefresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        if(!StringUtils.hasLength(refreshTokenRequestDTO.getRefreshToken())){
            return ResponseEntity.badRequest().build();
        }
        String token=refreshTokenService.reNewToken(refreshTokenRequestDTO.getRefreshToken());
        if(null==token){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(token);
    }
}
