package com.example.demo.Settings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class SettingsDTO {

    private String defaultSourceFormat;

    private String defaultTargetFormat;

    private Date createdAt;

}
