package com.sis.scrum.retro.repository;

import com.sis.scrum.retro.Retrospective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;
public interface RetroRepository extends JpaRepository<Retrospective, UUID> {
}
