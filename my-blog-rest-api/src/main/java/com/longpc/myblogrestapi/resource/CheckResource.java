package com.longpc.myblogrestapi.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class CheckResource {
    @GetMapping
    public ResponseEntity checkResource(){
        return ResponseEntity.ok().build();
    }
}
