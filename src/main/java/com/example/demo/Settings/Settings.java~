package com.example.demo.Settings;

import com.example.demo.Users.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "Settings")
@Data
@Builder
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer settingId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(length = 10)
    private String defaultSourceFormat;

    @Column(length = 10)
    private String defaultTargetFormat;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}