package com.sis.scrum.retro;


import com.sis.scrum.retro.controller.RetrospecitveController;

import com.sis.scrum.retro.repository.RetroPageRepository;
import com.sis.scrum.retro.repository.RetroRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class RetroControllerTest {

    @InjectMocks
    RetrospecitveController retrospecitveController;

    private static final Logger log = LoggerFactory.getLogger(RetroControllerTest.class);
    @Mock
    RetroRepository retroRepository;
    @Mock
    RetroPageRepository retroPageRepository;
    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
        retrospecitveController = new RetrospecitveController();
        retroRepository = mock(RetroRepository.class);
        MockitoAnnotations.initMocks(this);

    }


    @Test
    @DisplayName("fetch Retro Failed")
    public void fetchRetroFailed() throws Exception {

        log.info("Starting execution of fetchRetro");
        Retrospective retrospective = new Retrospective();
        retrospective.setDate(new Date());
        retrospective.setName(UUID.randomUUID());
        List<Retrospective> retrospectiveList = new ArrayList<>();
        retrospectiveList.add(retrospective);
        retrospecitveController.fetchRetro(0,2);

    }


    @Test
    @DisplayName("create Retro")
    public void createRetro() throws Exception {

        log.info("Starting execution of addRetro");
        Retrospective retrospective = new Retrospective();
        retrospective.setDate(new Date());
        retrospective.setName(UUID.randomUUID());
        List<Retrospective> retrospectiveList = new ArrayList<>();
        retrospectiveList.add(retrospective);
        ResponseEntity actualValue = retrospecitveController.createRetro(retrospective);

        Assertions.assertEquals(HttpStatus.CREATED, actualValue.getStatusCode());

    }

    @Test
    @DisplayName("update Retro")
    public void updateRetro() throws Exception {

        log.info("Starting execution of updateRetro");
        Retrospective retrospective = new Retrospective();
        retrospective.setDate(new Date());
        retrospective.setName(UUID.randomUUID());
        List<Retrospective> retrospectiveList = new ArrayList<>();
        retrospectiveList.add(retrospective);
        Mockito.when(retroRepository.getReferenceById(retrospective.getName())).thenReturn(retrospective);
        ResponseEntity actualValue = retrospecitveController.updateRetro(retrospective.getName(),retrospective);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, actualValue.getStatusCode());

    }

    @Test
    @DisplayName("update Retro Failed")
    public void updateRetro_500Eror() throws Exception {

        log.info("Starting execution of addRetro");
        ResponseEntity expectedValue = null;
        Retrospective retrospective = new Retrospective();
        retrospective.setDate(new Date());
        retrospective.setName(UUID.randomUUID());
        List<Retrospective> retrospectiveList = new ArrayList<>();
        retrospectiveList.add(retrospective);
        ResponseEntity actualValue = retrospecitveController.updateRetro(retrospective.getName(),retrospective);
        log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
        System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualValue.getStatusCode());

    }
}
