package com.example.demo.Files;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class FilesDTO {

    private String fileName;

    private int fileSize;

    private String fileType;

    private Date uploadDate;

}
