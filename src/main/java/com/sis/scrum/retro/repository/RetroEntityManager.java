package com.sis.scrum.retro.repository;

import com.sis.scrum.retro.Retrospective;

import java.util.List;

public interface RetroEntityManager {

    Retrospective saveRetro(Retrospective retrospective);
    List<String> getRetro();
    List<Retrospective> getRetros();
}
