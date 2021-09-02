package com.longpc.myblogsendemail.service;

import com.longpc.myblogsendemail.dto.ContactDTO;

public interface SendEmailService {
    void sendMail(ContactDTO contactDTO);
}
