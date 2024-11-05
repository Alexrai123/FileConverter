package com.example.demo.Conversions;

import com.example.demo.Files.Files;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Conversions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Column(length = 50, columnDefinition = "varchar(50) default 'pending'", nullable = false)
    private String status;

    @Column(length = 255)
    private String convertedFilePath;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date conversionDate;
}
