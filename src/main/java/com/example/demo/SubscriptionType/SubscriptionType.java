package com.example.demo.SubscriptionType;


import com.example.demo.Users.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "subscriptiontype")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subscription_type_id;

    @Column(name = "type_name", nullable = false, length = 50)
    private String typeName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(name = "file_size_limit", nullable = false)
    private int fileSizeLimit;

    @Column(name = "file_number_limit_per_day", nullable = false)
    private int fileNumberLimitPerDay;

    @JsonBackReference
    @OneToMany(mappedBy = "subscriptionType")
    private List<Users> users;
}
