package com.spring.course.web.controller;


import com.spring.course.web.DTO.ClubDto;
import com.spring.course.web.models.Club;
import com.spring.course.web.service.ClubService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/clubs/search")
    public String searchClub(@RequestParam("search") String search, Model model) {
        List<ClubDto> clubs = service.searchClubs(search);
        model.addAttribute("clubs", clubs);
        return "club-list";
    }

    @GetMapping("/clubs/{clubId}")
    public String retrieveClub(@PathVariable("clubId") long clubId, Model model) {
        ClubDto club = this.service.getClubById(clubId);
        model.addAttribute("club", club);
        return "club-detail";
    }

    @GetMapping("/clubs/create")
    public String createClub(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "club-create";
    }

    @PostMapping("/clubs/create")
    public String createClub(@Valid @ModelAttribute("club") ClubDto club,
                             BindingResult result,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("club", club);
            return "club-create";
        }

        this.service.saveClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClub(@PathVariable("clubId") long clubId, Model model) {
        ClubDto club = this.service.getClubById(clubId);
        model.addAttribute("club", club);

        return "club-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String editClub(@PathVariable("clubId") long clubId,
                           @Valid @ModelAttribute("club") ClubDto club,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/clubs/" + clubId + "/edit";
        }
        this.service.updateClub(clubId, club);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") long clubId, Model model) {
        String result = this.service.deleteClub(clubId);
        model.addAttribute("result", result);
        return "redirect:/clubs";
    }
}



