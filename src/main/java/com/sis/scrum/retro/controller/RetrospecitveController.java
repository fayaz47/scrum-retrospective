package com.sis.scrum.retro.controller;

import com.sis.scrum.retro.Retrospective;
import com.sis.scrum.retro.repository.RetroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class RetrospecitveController {
   @Autowired
    RetroRepository retroRepository;
    @GetMapping("/retros")
    public ResponseEntity fetchRetro() throws  Exception{

        List<Retrospective> retrospectives =  retroRepository.findAll();
        if(retrospectives != null) {
            return new ResponseEntity<>(retrospectives, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(retrospectives, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/retro")
    public ResponseEntity<Retrospective> createRetro(@RequestBody Retrospective retrospective) {
        try {
            Retrospective retrospective1 = retroRepository.save(retrospective);
            return new ResponseEntity<>(retrospective1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/retro")
    public ResponseEntity<Retrospective> updateRetro(@RequestParam UUID retroId,@RequestBody Retrospective retrospective) {
        try {
           Retrospective retrospective1 = retroRepository.getReferenceById(retroId);
            if(retrospective1 != null)
            retroRepository.save(retrospective);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
