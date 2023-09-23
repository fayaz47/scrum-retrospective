package com.sis.scrum.retro.controller;

import com.sis.scrum.retro.Feedback;
import com.sis.scrum.retro.FeedbackType;
import com.sis.scrum.retro.Item;
import com.sis.scrum.retro.Retrospective;
import com.sis.scrum.retro.repository.RetroPageRepository;
import com.sis.scrum.retro.repository.RetroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RetrospecitveController {
   @Autowired
    RetroRepository retroRepository;
    @Autowired
    RetroPageRepository retroPageRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(RetrospecitveController.class);

    @GetMapping("/retros")
    public ResponseEntity fetchRetro( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize) throws  Exception{

        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Retrospective> retrospectives =  retroPageRepository.findAll(paging);
        if(retrospectives != null) {
            LOGGER.info("List of Retros {}",retrospectives);
            return new ResponseEntity<>(retrospectives, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(retrospectives, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/retro")
    public ResponseEntity fetchRetroBydate( @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date date
                                      ) throws  Exception{


        List<Retrospective> retrospectives =  retroRepository.findAllByDate(date);
        if(retrospectives != null) {
            LOGGER.info("List of Retros {}",retrospectives);
            return new ResponseEntity<>(retrospectives, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(retrospectives, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/retro")
    public ResponseEntity<Retrospective> createRetro(@RequestBody Retrospective retrospective) {
        try {
            Retrospective retrospective1 = retroRepository.save(retrospective);
            LOGGER.info("Added  Retro {}",retrospective1);
            return new ResponseEntity<>(retrospective1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/retro/{retroId}/feedback")
    public ResponseEntity<Retrospective> createFeedbackForRetro(@PathVariable("retroId") UUID retroId,@RequestBody Item item) {
        try {
            Retrospective retrospective1 = retroRepository.findById(retroId).get();
            LOGGER.info("Adding  feedback to retro {}",retrospective1);
            List<Feedback> feedbackList = new ArrayList<>();
            Feedback feedback1 = new Feedback();
            feedback1.setFeedbackType((FeedbackType.valueOf(item.getFeedbackType())));
            feedback1.setBody(item.getBody());
            feedback1.setName(item.getName());
            feedback1.setRetrospective(retrospective1);
            feedbackList.add(feedback1);
            retrospective1.setFeedback(feedbackList);
            Retrospective retrospective =  retroRepository.save(retrospective1);
            LOGGER.info("Added  feedback to retro {}",retrospective);
            return new ResponseEntity<>(retrospective, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/retro/{retroId}/feedback/{feedbackId}")
    public ResponseEntity<Retrospective> updateFeedbackforRetro(@PathVariable("retroId") UUID retroId,@RequestBody Item item,@PathVariable("feedbackId") UUID feedbackId) {
        try {
            Retrospective retrospective1 = retroRepository.findById(retroId).get();
            LOGGER.info("updating  feedback to retro {}",retrospective1);
            List<Feedback> feedbackList = new ArrayList<>();
            Feedback feedback1 = new Feedback();
            feedback1.setFeedbackType((FeedbackType.valueOf(item.getFeedbackType())));
            feedback1.setBody(item.getBody());
            feedback1.setName(item.getName());
            feedback1.setId(feedbackId);
            feedback1.setRetrospective(retrospective1);
            feedbackList.add(feedback1);
            retrospective1.setFeedback(feedbackList);
            Retrospective retrospective =  retroRepository.save(retrospective1);
            LOGGER.info("updated  feedback to retro {}",retrospective);
            return new ResponseEntity<>(retrospective, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/retro")
    public ResponseEntity<Retrospective> updateRetro(@RequestParam UUID retroId,@RequestBody Retrospective retrospective) {
        try {
           Retrospective retrospective1 = retroRepository.getReferenceById(retroId);
            if(retrospective1 != null) {
                retroRepository.save(retrospective);
                LOGGER.info("Updated  Retro {}",retrospective1);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
