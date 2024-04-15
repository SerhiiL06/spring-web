package com.spring.course.web.service;

import com.spring.course.web.DTO.EventDto;
import jakarta.validation.constraints.Null;

import java.util.List;

public interface EventService {
    List<EventDto> findAllEvents(@Null Long clubId);
    void createEvent(EventDto eventDto);
    String deleteEventById(Long eventId);
    EventDto findEventById(Long eventId);

}
