package com.sis.scrum.retro.repository;

import com.sis.scrum.retro.Retrospective;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface RetroPageRepository extends PagingAndSortingRepository<Retrospective, UUID> {
}
