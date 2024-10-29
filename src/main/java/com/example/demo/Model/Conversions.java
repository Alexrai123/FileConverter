package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Conversions")
@Data
public class Conversions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer conversionId;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private Files file;

    @Column(nullable = false, length = 10)
    private String sourceFormat;

    @Column(nullable = false, length = 10)
    private String targetFormat;

    @Column(length = 50, columnDefinition = "varchar(50) default 'pending'")
    private String status;

    @Column(length = 255)
    private String convertedFilePath;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date conversionDate;
}
