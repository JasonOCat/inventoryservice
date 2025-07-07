package com.jason.inventoryservice.response;


import com.jason.inventoryservice.entity.Venue;
import lombok.Builder;

@Builder
public record EventInventoryResponse(
        String event,
        Long capacity,
        Venue venue
) {
}
