package com.spring.course.web.service;

import com.spring.course.web.DTO.ClubDto;
import com.spring.course.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(Club club);

}
