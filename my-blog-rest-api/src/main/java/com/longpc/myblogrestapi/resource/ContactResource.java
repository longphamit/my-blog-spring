package com.longpc.myblogrestapi.resource;

import com.longpc.myblogrestapi.config.call_service.MailServiceConfig;
import com.longpc.myblogrestapi.dto.ContactDTO;
import com.longpc.myblogrestapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/contact")
public class ContactResource {
    @Autowired
    private ContactService contactService;
    @Autowired
    private MailServiceConfig mailServiceConfig;
    @PostMapping
    public ResponseEntity insert(@RequestBody ContactDTO contactDTO) {
        contactService.insert(contactDTO);
        RestTemplate restTemplate = new RestTemplate();
        Thread t = new Thread(() -> {
            mailServiceConfig.sendMail(contactDTO);
        });
        t.start();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/auth")
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(contactService.getAll());
    }
    @DeleteMapping("/auth/{id}")
    public ResponseEntity deleteContactById(@PathVariable("id") String id) {
        contactService.deletedById(id);
        return ResponseEntity.ok().build();
    }
}
