package com.spring.course.web.service.impl;

import com.spring.course.web.DTO.ClubDto;
import com.spring.course.web.models.Club;
import com.spring.course.web.repository.ClubRepository;
import com.spring.course.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {
    private ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs(){
        List<Club> listOfClubs = this.clubRepository.findAll();
        return listOfClubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    };

    @Override
    public Club saveClub(Club club) {
        return this.clubRepository.save(club);
    }


    private ClubDto mapToClubDto(Club club) {
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .description(club.getDescription())
                .imageUrl(club.getImageUrl())
                .createOn(club.getCreateOn())
                .updateOn(club.getUpdateOn())
                .build();

        return clubDto;
    }
}
