package com.example.demo.SubscriptionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class SubscriptionTypeDTO {

    private String typeName;

    private String description;

    private double price;

    private int fileSizeLimit;

    private int fileNumberLimitPerDay;

    public SubscriptionTypeDTO(SubscriptionType subscriptionType) {
        this.typeName = subscriptionType.getTypeName();
        this.description = subscriptionType.getDescription();
        this.price = subscriptionType.getPrice();
        this.fileSizeLimit = subscriptionType.getFileSizeLimit();
        this.fileNumberLimitPerDay = subscriptionType.getFileNumberLimitPerDay();
    }

}
