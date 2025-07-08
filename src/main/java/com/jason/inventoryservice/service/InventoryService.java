package com.jason.inventoryservice.service;

import com.jason.inventoryservice.entity.Event;
import com.jason.inventoryservice.entity.Venue;
import com.jason.inventoryservice.repository.EventRepository;
import com.jason.inventoryservice.repository.VenueRepository;
import com.jason.inventoryservice.response.EventInventoryResponse;
import com.jason.inventoryservice.response.VenueInventoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    public List<EventInventoryResponse> getAllEvents() {
        final List<Event> events = eventRepository.findAll();

        return events.stream().map(event -> EventInventoryResponse.builder()
                        .eventId(event.getId())
                        .event(event.getName())
                        .capacity(event.getLeftCapacity())
                        .venue(event.getVenue())
                        .ticketPrice(event.getTicketPrice())
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


    public EventInventoryResponse getEventInventory(Long eventId) {
        final Event event = eventRepository.findById(eventId).orElseThrow(NoSuchElementException::new);

        return EventInventoryResponse.builder()
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .ticketPrice(event.getTicketPrice())
                .eventId(event.getId())
                .build();
    }

    public void updateEventCapacity(Long eventId, Long ticketBooked) {
        final Event event = eventRepository.findById(eventId).orElseThrow(RuntimeException::new);
        event.setLeftCapacity(event.getLeftCapacity() - ticketBooked);
        eventRepository.saveAndFlush(event);
        log.info("Updated event capacity for eventId: {} with tickets booked: {}", event, ticketBooked);
    }
}
