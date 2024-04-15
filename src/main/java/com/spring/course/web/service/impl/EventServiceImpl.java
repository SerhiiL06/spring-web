package com.spring.course.web.service.impl;

import com.spring.course.web.DTO.EventDto;
import com.spring.course.web.models.Club;
import com.spring.course.web.models.Event;
import com.spring.course.web.repository.ClubRepository;
import com.spring.course.web.repository.EventRepository;
import com.spring.course.web.service.EventService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public List<EventDto> findAllEvents(@Null Long clubId) {

        List<Event> events =  clubId != null ? eventRepository.findByClub(clubId) : eventRepository.findAll();

        List<EventDto> eventDtos = events.stream().map(this::mapEventToDto).collect(Collectors.toList());

        return eventDtos;
    }

    @Override
    public void createEvent(EventDto eventDto) {
        Optional<Club> club = this.clubRepository.findById(eventDto.getClubId());
        if (club.isPresent()) {
            Event event = this.mapDtoToEvent(eventDto);
            event.setClub(club.get());

            this.eventRepository.save(event);
        }

    }

    private Event mapDtoToEvent(EventDto event) {
        Event eventModel = Event.builder()
                .id(event.getId())
                .name(event.getName())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .build();
        return eventModel;

    }

    private EventDto mapEventToDto(Event event) {
        EventDto eventDto = EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .startTime(event.getStartTime())
                .club(event.getClub())
                .endTime(event.getEndTime())
                .build();
        return  eventDto;
    }


}
