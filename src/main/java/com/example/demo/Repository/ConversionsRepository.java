package com.example.demo.Repository;

import com.example.demo.Model.Conversions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConversionsRepository extends JpaRepository<Conversions, Integer> {
}
