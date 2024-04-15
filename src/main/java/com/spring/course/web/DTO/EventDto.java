package com.spring.course.web.DTO;


import com.spring.course.web.models.Club;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDto {
    private Long id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createOn;
    private Club club;
}
