package com.jason.inventoryservice.response;


import com.jason.inventoryservice.entity.Venue;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record EventInventoryResponse(
        Long eventId,
        String event,
        Long capacity,
        Venue venue,
        BigDecimal ticketPrice
) {
}
