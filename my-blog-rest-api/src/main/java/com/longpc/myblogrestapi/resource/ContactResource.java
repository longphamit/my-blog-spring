package com.longpc.myblogrestapi.resource;

import com.longpc.myblogrestapi.dto.ContactDTO;
import com.longpc.myblogrestapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactResource {
    @Autowired
    private ContactService contactService;
    @PostMapping
    public ResponseEntity insert(ContactDTO contactDTO){
        contactService.insert(contactDTO);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok().body(contactService.getAll());
    }
}
