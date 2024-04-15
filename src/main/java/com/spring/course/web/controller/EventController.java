package com.spring.course.web.controller;

import com.spring.course.web.DTO.EventDto;
import com.spring.course.web.models.Event;
import com.spring.course.web.service.EventService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/clubs/events")
    public String clubEvents(@Nullable @RequestParam("club_id") Long clubId, Model model) {
        List<EventDto> events = this.eventService.findAllEvents(clubId);
        model.addAttribute("events", events);
        return "club-events";
    }

    @GetMapping("/clubs/events/{eventId}")
    public String retrieveEvent(@PathVariable("eventId") Long eventId, Model model) {
        EventDto event = this.eventService.findEventById(eventId);
        model.addAttribute("event", event);
        return "event-detail";

    }

    @GetMapping("/clubs/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId, Model model) {
        this.eventService.deleteEventById(eventId);
        return  "redirect:/clubs/events";
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
