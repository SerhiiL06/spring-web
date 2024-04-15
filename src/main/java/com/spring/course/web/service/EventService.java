package com.spring.course.web.service;

import com.spring.course.web.DTO.EventDto;
import com.spring.course.web.models.Event;

public interface EventService {
    public void createEvent(EventDto event);
}
