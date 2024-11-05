package com.example.demo.SubscriptionType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType, Integer> {
    Optional<SubscriptionType> findByTypeName(String typeName);
}
