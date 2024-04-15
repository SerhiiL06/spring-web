package com.spring.course.web.service.impl;

import com.spring.course.web.DTO.ClubDto;
import com.spring.course.web.mappings.ClubMapping;
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
    private final ClubMapping clubMapping;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
        this.clubMapping = new ClubMapping();
    }

    @Override
    public List<ClubDto> findAllClubs(){
        List<Club> listOfClubs = this.clubRepository.findAll();
        return listOfClubs.stream().map((club) -> this.clubMapping.mapToClubDto(club)).collect(Collectors.toList());
    };

    @Override
    public Club saveClub(ClubDto club) {
        Club clubModel = this.clubMapping.mapToClub(club);
        return this.clubRepository.save(clubModel);
    }


    @Override
    public void updateClub(Long clubId, ClubDto clubDto) {
        Club club = this.clubMapping.mapToClub(clubDto);
        club.setId(clubId);
        this.clubRepository.save(club);
    }


    @Override
    public List<ClubDto> searchClubs(String search) {
        List<Club> clubs = this.clubRepository.searchClubs(search);
        return clubs.stream().map((club) -> this.clubMapping.mapToClubDto(club)).collect(Collectors.toList());
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
        return this.clubMapping.mapToClubDto(club);
    }









}
