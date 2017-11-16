package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.models.Event;
import com.eduardoportfolio.eventmanagementsystem.services.EventService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by Eduardo on 16/11/17.
 */
public class EventControllerTest {

    @Mock
    EventService eventService;

    EventController eventController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        eventController = new EventController(eventService);
    }

    @Test
    public void eventFormTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();

        mockMvc.perform(get("/event/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("event/eventForm"));

    }

    @Test
    public void saveEventTest() throws Exception {
    //Todo implement save test
    }

    @Test
    public void getEventByIdTest() throws Exception {
        Event event = new Event();
        event.setEventId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();

        when(eventService.getEventById(anyLong())).thenReturn(event);

        mockMvc.perform(get("/event/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("event/showEvent"));
    }

}