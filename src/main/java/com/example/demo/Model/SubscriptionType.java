package com.example.demo.Model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "subscriptiontype")
@Data
public class SubscriptionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subscription_type_id;

    @Column(nullable = false, length = 50)
    private String typeName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int fileSizeLimit;

    @Column(nullable = false)
    private int fileNumberLimitPerDay;

    @OneToMany(mappedBy = "subscriptionType")
    private List<Users> users;
}
