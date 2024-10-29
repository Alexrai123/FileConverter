package com.example.demo.Controller;

import com.example.demo.Model.SubscriptionType;
import com.example.demo.Model.Users;
import com.example.demo.Repository.SubscriptionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptionTypes")
public class SubscriptionTypeController {

    @Autowired
    private final SubscriptionTypeRepository subscriptionTypeRepository;

    @GetMapping
    public List<SubscriptionType> getAllSubscriptionTypes() {
        return subscriptionTypeRepository.findAll();
    }

    @GetMapping("/{subscriptionTypeId}")
    public SubscriptionType getSubscriptionTypeById(@PathVariable Integer subscriptionTypeId) {
        Optional<SubscriptionType> subscriptionType = subscriptionTypeRepository.findById(subscriptionTypeId);
        if (subscriptionType.isPresent()) {
            return subscriptionType.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription type not found");
        }
    }

    @PostMapping
    public SubscriptionType createSubscriptionType(@RequestBody SubscriptionType subscriptionType) {
        subscriptionTypeRepository.save(subscriptionType);
        return subscriptionType;
    }

    @PutMapping
    public SubscriptionType updateSubscriptionType(@RequestBody SubscriptionType subscriptionTypeToUpdate) {
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(subscriptionTypeToUpdate.getSubscription_type_id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription type not found"));
        subscriptionType.setTypeName(subscriptionTypeToUpdate.getTypeName());
        subscriptionType.setDescription(subscriptionTypeToUpdate.getDescription());
        subscriptionType.setPrice(subscriptionTypeToUpdate.getPrice());
        subscriptionType.setFileSizeLimit(subscriptionTypeToUpdate.getFileSizeLimit());
        subscriptionType.setFileNumberLimitPerDay(subscriptionTypeToUpdate.getFileNumberLimitPerDay());
        return subscriptionTypeRepository.save(subscriptionType);
    }

    @DeleteMapping("/{subscriptionTypeId}")
    public void deleteSubscriptionType(@PathVariable Integer subscriptionTypeId) {
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(subscriptionTypeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription type not found"));
        subscriptionTypeRepository.delete(subscriptionType);
    }
}
