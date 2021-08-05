package com.longpc.myblogrestapi.resource;

import com.longpc.myblogrestapi.dto.ContactDTO;
import com.longpc.myblogrestapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactResource {
    @Autowired
    private ContactService contactService;
    @PostMapping
    public ResponseEntity insert(@RequestBody  ContactDTO contactDTO){
        contactService.insert(contactDTO);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/auth")
    public ResponseEntity getAll(){
        return ResponseEntity.ok().body(contactService.getAll());
    }
}
