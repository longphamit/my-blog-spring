package com.longpc.myblogrestapi.resource;
import com.longpc.myblogrestapi.bean.JwtCustomBean;
import com.longpc.myblogrestapi.dto.AuthenDTO;
import com.longpc.myblogrestapi.dto.AuthenResponseDTO;
import com.longpc.myblogrestapi.entity.AuthenEntity;
import com.longpc.myblogrestapi.entity.RefreshTokenEntity;
import com.longpc.myblogrestapi.repository.RefreshTokenRepo;
import com.longpc.myblogrestapi.service.AuthenService;
import com.longpc.myblogrestapi.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authen")
public class AuthenResource {
    @Autowired
    AuthenService authenService;
    @Autowired
    RefreshTokenRepo refreshTokenRepo;
    @Autowired
    JwtCustomBean jwtCustomBean;
    @Autowired
    RefreshTokenService refreshTokenService;
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthenDTO authenDTO) {
        AuthenResponseDTO authenResponseDTO = authenService.login(authenDTO);
        if (authenResponseDTO != null) {
            List<String> exposeHeader= new ArrayList();
            String token= jwtCustomBean.generateJwtToken(authenResponseDTO);
            RefreshTokenEntity refreshTokenEntity= refreshTokenService.createRefreshToken(authenResponseDTO.getId());
            authenResponseDTO.setRefreshToken(refreshTokenEntity);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("X-LONGPC-ACCESS-TOKEN",token);
            exposeHeader.add("X-LONGPC-ACCESS-TOKEN");
            responseHeaders.setAccessControlExposeHeaders(exposeHeader);
            return ResponseEntity.ok().headers(responseHeaders).body(authenResponseDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/regist")
    public ResponseEntity regist(@RequestBody AuthenDTO authenDTO) {
        AuthenEntity authenEntity = authenService.register(authenDTO);
        if (authenEntity == null) {
            return ResponseEntity.badRequest().build();
        }
        authenEntity.setPassword("");
        return ResponseEntity.ok().body(authenEntity);
    }
}
