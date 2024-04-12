package com.spring.course.web.DTO;


import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Builder
public class ClubDto {
    private Long id;
    private String title;
    private String imageUrl;
    private String description;
    private LocalDateTime createOn;
    private LocalDateTime updateOn;
}
