package com.spring.course.web.service.impl;

import com.spring.course.web.DTO.EventDto;
import com.spring.course.web.models.Club;
import com.spring.course.web.models.Event;
import com.spring.course.web.repository.ClubRepository;
import com.spring.course.web.repository.EventRepository;
import com.spring.course.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


}
