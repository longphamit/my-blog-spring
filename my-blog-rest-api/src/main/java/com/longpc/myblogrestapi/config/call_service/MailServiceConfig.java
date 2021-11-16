package com.longpc.myblogrestapi.config.call_service;

import com.longpc.myblogrestapi.dto.ContactDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mail-service")
public interface MailServiceConfig {
    @GetMapping("/mail/send")
    Object sendMail(
            @RequestBody ContactDTO contactDTO
    );
}
