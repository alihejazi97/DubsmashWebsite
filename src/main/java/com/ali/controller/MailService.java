package com.ali.controller;


import com.ali.database.model.User;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class MailService {


    @Autowired
    JavaMailSender mailSender;

    @Value("${hostname}")
    private String hostname;

    @Bean
    public Cache<User,String> getCache() {
         return CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();
    }

    public void sendVerificationEmail(String email) {
        String verification = UUID.randomUUID().toString();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setText(hostname + "?verify=" + verification);
        msg.setTo(email);
        msg.setFrom("noone@noone.com");
        msg.setSubject("Verification Email");
        JavaMailSenderImpl sender= (JavaMailSenderImpl) mailSender;
        System.out.println("username : " + sender.getUsername());
        System.out.println("password : " + sender.getPassword());
        System.out.println("host : " + sender.getHost());
        mailSender.send(msg);
    }
}
