package com.longpc.myblogsendemail.resource;

import com.longpc.myblogsendemail.dto.ContactDTO;
import com.longpc.myblogsendemail.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class SendEmailResource {
    @Autowired
    SendEmailService sendEmailService;
    @PostMapping
    public ResponseEntity sendMail(@RequestBody ContactDTO contactDTO){
        sendEmailService.sendMail(contactDTO);
        System.out.println("send email success");
        return ResponseEntity.ok().build();
    }
}
