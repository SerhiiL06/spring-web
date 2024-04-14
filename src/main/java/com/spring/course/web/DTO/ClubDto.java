package com.spring.course.web.DTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


import java.time.LocalDateTime;

@Data
@Builder
public class ClubDto {
    private Long id;
    @NotNull(message = "title must be not null")
    private String title;
    private String imageUrl;
    @Length(min = 10)
    private String description;
    private LocalDateTime createOn;
    private LocalDateTime updateOn;
}
