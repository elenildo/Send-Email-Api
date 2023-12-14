package com.dev.email.model;

import com.dev.email.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sentEmailDate;
    private StatusEmail statusEmail;
}
