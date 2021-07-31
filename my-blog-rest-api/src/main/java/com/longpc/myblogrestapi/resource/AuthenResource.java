package com.longpc.myblogrestapi.resource;
import com.longpc.myblogrestapi.dto.AuthenDTO;
import com.longpc.myblogrestapi.entity.AuthenEntity;
import com.longpc.myblogrestapi.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authen")
public class AuthenResource {
    @Autowired
    AuthenService authenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenDTO authenDTO) {
        AuthenEntity authenEntity = authenService.login(authenDTO);
        if (authenEntity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(authenEntity);
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
