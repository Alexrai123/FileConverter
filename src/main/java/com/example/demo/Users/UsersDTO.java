package com.example.demo.Users;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UsersDTO {

    private String email;

    private String password;

    private String subscriptionType;

    private double price; // Prețul abonamentului

    private int fileSizeLimit; // Limita pentru dimensiunea fișierului

    private int fileNumberLimitPerDay; // Limita pentru numărul de fișiere pe zi

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date subscriptionStartDate;

    private Integer nrOfFilesConvertedPerMonth;
}