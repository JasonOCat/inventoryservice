package com.jason.inventoryservice.controller;

import com.jason.inventoryservice.response.EventInventoryResponse;
import com.jason.inventoryservice.response.VenueInventoryResponse;
import com.jason.inventoryservice.service.InventoryService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/inventory/events")
    public List<EventInventoryResponse> inventoryGetAllEvents() {
        return inventoryService.getAllEvents();
    }

    @GetMapping("inventory/venue/{venueId}")
    public VenueInventoryResponse inventoryByVenueId(@PathVariable("venueId") Long venueId) {
        return inventoryService.getVenueInformation(venueId);
    }

    @GetMapping("inventory/event/{eventId}")
    public EventInventoryResponse inventoryForEvent(@PathVariable("eventId") Long eventId) {
        return inventoryService.getEventInventory(eventId);
    }

    @PutMapping("inventory/event/{eventId}/capacity/{capacity}")
    public ResponseEntity<Void> updateEventCapacity(
            @PathVariable("eventId") Long eventId,
            @PathVariable("capacity") Long ticketBooked
    ) {
        inventoryService.updateEventCapacity(eventId, ticketBooked);
        return ResponseEntity.ok().build();
    }
}
