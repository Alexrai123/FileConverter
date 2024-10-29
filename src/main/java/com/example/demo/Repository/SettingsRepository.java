package com.example.demo.Repository;

import com.example.demo.Model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Integer> {
}
