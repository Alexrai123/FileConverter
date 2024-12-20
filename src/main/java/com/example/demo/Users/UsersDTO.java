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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date subscriptionStartDate;

    private Integer nrOfFilesConvertedPerMonth;

}
