package com.dev.email.controller;

import com.dev.email.dto.EmailDto;
import com.dev.email.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("sending-email")
public class EmailController {

    private EmailService emailService;

    @PostMapping
    public ResponseEntity<?> sendingEmail(@RequestBody @Valid EmailDto emailDto) {
        return new ResponseEntity<>(emailService.sendEmailWithAttachment(emailDto), HttpStatus.CREATED);
    }
}
