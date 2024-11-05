package com.example.demo.Conversions;

import com.example.demo.Files.Files;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ConversionsDTO {

    private String fileName;

    private String sourceFormat;

    private String targetFormat;

    private String status;

    private String convertedFilePath;

    private Date conversionDate;

}
