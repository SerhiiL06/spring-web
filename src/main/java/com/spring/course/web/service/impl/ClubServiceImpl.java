package com.spring.course.web.service.impl;

import com.spring.course.web.DTO.ClubDto;
import com.spring.course.web.models.Club;
import com.spring.course.web.repository.ClubRepository;
import com.spring.course.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public Club saveClub(ClubDto club) {
        Club clubModel = this.mapToClub(club);
        return this.clubRepository.save(clubModel);
    }


    @Override
    public void updateClub(ClubDto clubDto) {
        Club club = this.mapToClub(clubDto);
        this.clubRepository.save(club);
    }


    private Club mapToClub(ClubDto clubDto) {
        Club clubBuid = Club.builder()
                .title(clubDto.getTitle())
                .description(clubDto.getDescription())
                .build();
        return clubBuid;
    }

    @Override
    public String deleteClub(Long id) {
        Optional<Club> club = this.clubRepository.findById(id);

        if (club.isEmpty()) {
            return "Club not found";
        }

        this.clubRepository.deleteById(id);
        return "Club deleted";
    }


    @Override
    public ClubDto getClubById(Long id) {
        Club club =  this.clubRepository.findById(id).get();
        return this.mapToClubDto(club);
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
