package com.spring.course.web.service;

import com.spring.course.web.DTO.ClubDto;
import com.spring.course.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(ClubDto club);
    void updateClub(ClubDto club);
    ClubDto getClubById(Long id);
    String deleteClub(Long id);
    List<ClubDto> searchClubs(String keyword);


}
