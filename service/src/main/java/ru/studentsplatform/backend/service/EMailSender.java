package ru.studentsplatform.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


/**
 * Simple class for sending e-mail message.
 * Address "from" set up in configuration.
 * Can be extended in future by overloading methods
 * @author Danila K.
 * */

@Service
public class EMailSender {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Method to send email messages
     * @param to is email to send
     * @param subject is message subject
     * @param body is main text of html
     * */
    public void send(String to, String subject, String body){

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        javaMailSender.send(mailMessage);
    }
}
