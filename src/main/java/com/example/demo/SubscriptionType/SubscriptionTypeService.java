package com.example.demo.SubscriptionType;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    public SubscriptionType create(SubscriptionTypeDTO subscriptionTypeDto) {

        return subscriptionTypeRepository.save(SubscriptionType.builder()
                .typeName(subscriptionTypeDto.getTypeName())
                .description(subscriptionTypeDto.getDescription())
                .price(subscriptionTypeDto.getPrice())
                .fileSizeLimit(subscriptionTypeDto.getFileSizeLimit())
                .fileNumberLimitPerDay(subscriptionTypeDto.getFileNumberLimitPerDay())
                .build());
    }


    public SubscriptionType getOne(Integer idSubscriptionType) {
        return subscriptionTypeRepository.findById(idSubscriptionType).orElseThrow(() -> new EntityNotFoundException("Subscription type not found"));
    }

    public SubscriptionType getSubscriptionTypeByName(String typeName) {
        return subscriptionTypeRepository.findByTypeName(typeName).orElseThrow(() -> new EntityNotFoundException("Subscription type not found"));
    }

    public List<SubscriptionType> getAll(){
        return subscriptionTypeRepository.findAll();
    }

    public List<SubscriptionTypeDTO> getAllDTO(){
        return subscriptionTypeRepository.findAll().stream()
                .map(subscriptionType -> new SubscriptionTypeDTO(subscriptionType.getTypeName(), subscriptionType.getDescription(), subscriptionType.getPrice(), subscriptionType.getFileSizeLimit(), subscriptionType.getFileNumberLimitPerDay()))
                .toList();
    }

    public SubscriptionType update(Integer id, SubscriptionTypeDTO subscriptionTypeDTO) {
        SubscriptionType existingSubscriptionType = subscriptionTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription type not found"));

        existingSubscriptionType = SubscriptionType.builder()
                .subscription_type_id(existingSubscriptionType.getSubscription_type_id())  // Keep the original ID
                .typeName(subscriptionTypeDTO.getTypeName())
                .description(subscriptionTypeDTO.getDescription())
                .price(subscriptionTypeDTO.getPrice())
                .fileSizeLimit(subscriptionTypeDTO.getFileSizeLimit())
                .fileNumberLimitPerDay(subscriptionTypeDTO.getFileNumberLimitPerDay())
                .build();

        return subscriptionTypeRepository.save(existingSubscriptionType);
    }


    public void delete(Integer idSubscriptionType) throws Exception {
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(idSubscriptionType).orElseThrow(() -> new EntityNotFoundException("Subscription type not found"));
        subscriptionTypeRepository.delete(subscriptionType);
    }
}
