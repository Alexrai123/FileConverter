package com.example.demo.Settings;

import com.example.demo.Users.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettingsService {

    private final SettingsRepository settingsRepository;
    private final UsersRepository usersRepository;

    public Settings create(SettingsDTO settings) {
        return settingsRepository.save(Settings.builder()
                .defaultSourceFormat(settings.getDefaultSourceFormat())
                .defaultTargetFormat(settings.getDefaultTargetFormat())
                .createdAt(settings.getCreatedAt())
                .build());
    }

    public Settings getOne(Integer idSetting) {
        return settingsRepository.findById(idSetting).orElseThrow(() -> new EntityNotFoundException("Setting not found"));
    }

    public List<Settings> getAll() {
        return settingsRepository.findAll();
    }

    public Settings update(Settings settingsToUpdate) {
        Settings existingSettings = getOne(settingsToUpdate.getSettingId());
        existingSettings.setDefaultSourceFormat(settingsToUpdate.getDefaultSourceFormat());
        existingSettings.setDefaultTargetFormat(settingsToUpdate.getDefaultTargetFormat());
        return settingsRepository.save(existingSettings);
    }

    public void delete(Integer idSetting) {
        Settings settingsToDelete = getOne(idSetting);
        settingsRepository.delete(settingsToDelete);
    }

}
