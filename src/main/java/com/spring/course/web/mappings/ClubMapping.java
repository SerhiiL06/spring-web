package com.spring.course.web.mappings;

import com.spring.course.web.DTO.ClubDto;
import com.spring.course.web.models.Club;

public class ClubMapping {

    public ClubDto mapToClubDto(Club club) {
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
    public Club mapToClub(ClubDto clubDto) {
        Club clubBuid = Club.builder()
                .title(clubDto.getTitle())
                .description(clubDto.getDescription())
                .build();
        return clubBuid;
    }


}
