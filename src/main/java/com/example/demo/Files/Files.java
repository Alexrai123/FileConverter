package com.example.demo.Files;

import com.example.demo.Users.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Files")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer fileId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(nullable = false, length = 255)
    private String fileName;

    @Column(nullable = false)
    private int fileSize;

    @Column(length = 10)
    private String fileType;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
}