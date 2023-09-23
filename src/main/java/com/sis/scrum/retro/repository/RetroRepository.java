package com.sis.scrum.retro.repository;

import com.sis.scrum.retro.Retrospective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;
public interface RetroRepository extends JpaRepository<Retrospective, UUID> {
    @Query("select a from Retrospective a where a.date = ?1")
    List<Retrospective> findAllByDate(Date date);
}
