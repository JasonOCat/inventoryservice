package com.jason.inventoryservice.response;

import lombok.Builder;

@Builder
public record VenueInventoryResponse(
        Long venueId,
        String venueName,
        Long totalCapacity
){
}
