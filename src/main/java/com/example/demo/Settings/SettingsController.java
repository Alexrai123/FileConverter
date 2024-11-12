package com.example.demo.Settings;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController
@RequiredArgsConstructor
@RequestMapping("/settings")
public class SettingsController {

    private final SettingsService settingsService;

    ////////////////////////////////
    @GetMapping("/test")
    public String getSettings(Model model) {
        List<Settings> settings = settingsService.getAll();
        model.addAttribute("settings", settings);
        return "settings";
    }
    ////////////////////////////////

    @GetMapping
    public List<Settings> getAll() {
        return settingsService.getAll();
    }

    @GetMapping("/{idSetting}")
    public Settings getOne(@PathVariable Integer idSetting) {
        return settingsService.getOne(idSetting);
    }

    @PostMapping
    public Settings create(@RequestBody SettingsDTO settings) {
        return settingsService.create(settings);
    }

    @PutMapping
    public Settings update(@RequestBody Settings settingsToUpdate) {
        return settingsService.update(settingsToUpdate);
    }

    @DeleteMapping("/{idSetting}")
    public void delete(@PathVariable Integer idSetting) {
        settingsService.delete(idSetting);
    }
}
