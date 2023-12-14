package com.dev.email.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmailDto {
    @NotBlank
    private String ownerRef;

    @Email
    @NotBlank
    private String emailFrom;

    @Email
    @NotBlank
    private String emailTo;

    @NotBlank
    private String subject;

    @NotBlank
    private String text;
}
