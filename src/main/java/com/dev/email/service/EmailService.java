package com.dev.email.service;

import com.dev.email.dto.EmailDto;
import com.dev.email.enums.StatusEmail;
import com.dev.email.model.Email;
import com.dev.email.respository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmailService {
    private EmailRepository emailRepository;
    private JavaMailSender javaMailSender;

    public Email sendEmail(EmailDto emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        email.setSentEmailDate(LocalDateTime.now());

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            javaMailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);

        }catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        }finally {
            emailRepository.save(email);
        }
        return email;
    }
    public Email sendEmailWithAttachment(EmailDto emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        email.setSentEmailDate(LocalDateTime.now());

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false);

            helper.setFrom(email.getEmailFrom());
            helper.setTo(email.getEmailTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText(),true);

            javaMailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);
        }catch (MessagingException e){
            email.setStatusEmail(StatusEmail.ERROR);
        }finally {
            emailRepository.save(email);
        }

        return email;

    }
}
