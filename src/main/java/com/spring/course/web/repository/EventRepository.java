package com.spring.course.web.repository;

import com.spring.course.web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.club.id = :clubId")
    List<Event> findByClub(Long clubId);
}
