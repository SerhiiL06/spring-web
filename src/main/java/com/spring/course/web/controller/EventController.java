package com.spring.course.web.controller;

import com.spring.course.web.DTO.EventDto;
import com.spring.course.web.models.Event;
import com.spring.course.web.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/clubs/events/create")
    public String createEvent(Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        return "event-create";
    }

    @PostMapping("/clubs/events/create")
    public String createEvent(@Valid @ModelAttribute("event") EventDto eventDto, BindingResult result) {

        if (result.hasErrors()) {
            return "event-create";
        }

        this.eventService.createEvent(eventDto);
        return "redirect:/clubs";
    }
}
