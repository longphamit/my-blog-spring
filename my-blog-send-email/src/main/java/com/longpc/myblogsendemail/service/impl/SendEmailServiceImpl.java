package com.longpc.myblogsendemail.service.impl;

import com.longpc.myblogsendemail.dto.ContactDTO;
import com.longpc.myblogsendemail.service.SendEmailService;
import com.longpc.myblogsendemail.service.ThymeLeafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Service
public class SendEmailServiceImpl implements SendEmailService {
    private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";
    @Value("${config.mail.host}")
    private String host;
    @Value("${config.mail.port}")
    private String port;
    @Value("${config.mail.username}")
    private String email;
    @Value("${config.mail.password}")
    private String password;
    @Autowired
    ThymeLeafService thymeleafService;

    public void sendMail(ContactDTO contactDTO) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        Message message = new MimeMessage(session);
        try {
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(contactDTO.getEmail())});
            message.setFrom(new InternetAddress(email));
            message.setSubject("Devmon Blog");
            message.setContent(thymeleafService.getContent(contactDTO), CONTENT_TYPE_TEXT_HTML);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
