package com.example.demo.Users;

import com.example.demo.SubscriptionType.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "subscription_type_id", nullable = false)
    @JsonIgnoreProperties("users")
    private SubscriptionType subscriptionType;

    @Column(nullable = false, name = "subscription_start_date")
    @Temporal(TemporalType.DATE)
    private Date subscriptionStartDate;
}
