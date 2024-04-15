package com.spring.course.web.service.impl;

import com.spring.course.web.DTO.EventDto;
import com.spring.course.web.models.Event;
import com.spring.course.web.repository.EventRepository;
import com.spring.course.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void createEvent(EventDto eventDto) {
        Event event = this.mapDtoToEvent(eventDto);
        this.eventRepository.save(event);
    }

    private Event mapDtoToEvent(EventDto event) {
        Event eventModel =Event.builder()
                .id(event.getId())
                .name(event.getName())
                .club(event.getClub())
                .build();
        return eventModel;

    }


}
