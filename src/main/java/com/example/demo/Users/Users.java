package com.example.demo.Users;

import com.example.demo.SubscriptionType.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "subscription_type_id", nullable = false)
    @JsonManagedReference
    private SubscriptionType subscriptionType;

    @Column(nullable = false, name = "subscription_start_date")
    @Temporal(TemporalType.DATE)
    private Date subscriptionStartDate;

    @Column(name = "nr_of_files_converted_per_month")
    private Integer nrOfFilesConvertedPerMonth;
}
