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
    @GetMapping(value = "/retros}", produces = {"application/json"})
    public ResponseEntity<Object> fetchRetro() throws  Exception{

        List<Retrospective> consent =  retroRepository.getRetros();
        if(consent != null) {
            return new ResponseEntity<>(consent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(consent, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/retro")
    public ResponseEntity<Retrospective> createRetro(@RequestBody Retrospective retrospective) {
        try {
            Retrospective retrospective1 = retroRepository.saveRetro(retrospective);
            return new ResponseEntity<>(retrospective1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/retro")
    public ResponseEntity<Retrospective> updateRetro(@RequestParam UUID retroId,@RequestBody Retrospective retrospective) {
        try {
           Retrospective retrospective1 = retroRepository.getRetros().stream().filter(i -> i.equals(retroId)).findFirst().orElse(null);
            if(retrospective1 != null)
            retroRepository.saveRetro(retrospective);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
