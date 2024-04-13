package com.spring.course.web.controller;


import com.spring.course.web.DTO.ClubDto;
import com.spring.course.web.models.Club;
import com.spring.course.web.service.ClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClubController {
    private final ClubService service;

    @Autowired
    public ClubController(ClubService service) {
        this.service = service;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDto> clubs = service.findAllClubs();
        String name = "Serhii";
        model.addAttribute("clubs", clubs);
        model.addAttribute("name", name);
        return "club-list";

    }

    @GetMapping("/clubs/create")
    public String createClub(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "club-create";
    }

    @PostMapping("/clubs/create")
    public String createClub(@ModelAttribute("club") Club club) {
        this.service.saveClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClub(@PathVariable("clubId") long clubId, Model model) {
        ClubDto club = this.service.getClubById(clubId);
        model.addAttribute("club", club);

        return "club-edit";
    }

//    @PostMapping("/clubs/{clubId}/edit")
//    public String editClub(@PathVariable("clubId") long clubId, @ModelAttribute("club") ClubDto club) {
//        club.setId(clubId);
//        this.service.updateClud(club);
//        return "redirect:/clubs/";
//    }
}
