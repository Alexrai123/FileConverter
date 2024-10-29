package com.example.demo.Controller;

import com.example.demo.Model.Settings;
import com.example.demo.Repository.SettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private final SettingsRepository settingsRepository;

    @GetMapping
    public ResponseEntity<List<Settings>> getAllSettings(){
        return ResponseEntity.ok(settingsRepository.findAll());
    }

    @GetMapping("/{settingId}")
    public Settings getSettingById(@PathVariable Integer settingId) {
        Optional<Settings> setting = settingsRepository.findById(settingId);
        if (setting.isPresent()) {
            return setting.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Setting not found");
        }
    }

    @PostMapping
    public Settings createSettings(@RequestBody Settings settings) {
        settingsRepository.save(settings);
        return settings;
    }

    @PutMapping
    public Settings updateSettings(@RequestBody Settings settingsToUpdate) {
        Settings settings = settingsRepository.findById(settingsToUpdate.getSettingId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setting not found"));
        settings.setDefaultSourceFormat(settingsToUpdate.getDefaultSourceFormat());
        settings.setDefaultTargetFormat(settingsToUpdate.getDefaultTargetFormat());
        settings.setCreatedAt(settingsToUpdate.getCreatedAt());
        settingsRepository.save(settings);
        return settings;
    }

    @DeleteMapping("/{settingId}")
    public void deleteSettings(@PathVariable Integer settingId) {
        Settings settings = settingsRepository.findById(settingId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setting not found"));
        settingsRepository.delete(settings);
    }
}
