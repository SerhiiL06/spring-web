package com.spring.course.web.controller;


import com.spring.course.web.DTO.ClubDto;
import com.spring.course.web.service.ClubService;
import com.spring.course.web.service.impl.ClubServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClubController {
    private ClubService service;

    @Autowired
    public ClubController(ClubService service) {
        this.service = service;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDto> clubs = service.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "club-list";

    }
}
