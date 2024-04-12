package com.spring.course.web.repository;

import com.spring.course.web.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;


public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByTitle(String title);
}
