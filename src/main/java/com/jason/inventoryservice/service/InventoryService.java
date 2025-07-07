package com.jason.inventoryservice.service;

import com.jason.inventoryservice.entity.Event;
import com.jason.inventoryservice.entity.Venue;
import com.jason.inventoryservice.repository.EventRepository;
import com.jason.inventoryservice.repository.VenueRepository;
import com.jason.inventoryservice.response.EventInventoryResponse;
import com.jason.inventoryservice.response.VenueInventoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    public List<EventInventoryResponse> getAllEvents() {
        final List<Event> events = eventRepository.findAll();

        return events.stream().map(event -> EventInventoryResponse.builder()
                        .event(event.getName())
                        .capacity(event.getLeftCapacity())
                        .build())
                .toList();
    }

    public VenueInventoryResponse getVenueInformation(final Long venueId) {
        final Venue venue = venueRepository.findById(venueId).orElseThrow(NoSuchElementException::new);

        return VenueInventoryResponse.builder()
                .venueId(venue.getId())
                .venueName(venue.getName())
                .totalCapacity(venue.getTotalCapacity())
                .build();
    }


}
