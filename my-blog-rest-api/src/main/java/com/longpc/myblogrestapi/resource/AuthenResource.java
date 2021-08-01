package com.longpc.myblogrestapi.resource;
import com.longpc.myblogrestapi.bean.JwtCustomBean;
import com.longpc.myblogrestapi.dto.AuthenDTO;
import com.longpc.myblogrestapi.entity.AuthenEntity;
import com.longpc.myblogrestapi.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/authen")
public class AuthenResource {
    @Autowired
    AuthenService authenService;
    @Autowired
    JwtCustomBean jwtCustomBean;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenDTO authenDTO) {
        AuthenEntity authenEntity = authenService.login(authenDTO);
        if (authenEntity != null) {
            List<String> exposeHeader= new ArrayList();
            String token= jwtCustomBean.generateJwtToken(authenEntity);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("X-LONGPC-ACCESS-TOKEN",token);
            exposeHeader.add("X-LONGPC-ACCESS-TOKEN");
            responseHeaders.setAccessControlExposeHeaders(exposeHeader);
            return ResponseEntity.ok().headers(responseHeaders).body(authenEntity);
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