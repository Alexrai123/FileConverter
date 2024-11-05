package com.example.demo.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UsersDTO {

    private String username;

    private String email;

    private String password;

    private String subscriptionType;

    private Date subscriptionStartDate;

}
