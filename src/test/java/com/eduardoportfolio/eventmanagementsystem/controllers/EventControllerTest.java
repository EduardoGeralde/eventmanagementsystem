package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.models.Event;
import com.eduardoportfolio.eventmanagementsystem.services.EventService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import static org.junit.Assert.*;

/**
 * Created by Eduardo on 15/11/17.
 */
public class EventControllerTest {

    @Mock
    EventService eventService;
    @Mock
    Model model;

    EventController eventController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        eventController = new EventController(eventService);
    }

    @Test
    public void testMockMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();

        mockMvc.perform(get("/eventForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration/eventForm"));
        mockMvc.perform(get("/eventList"))
                .andExpect(status().isOk())
                .andExpect(view().name("events"));
        mockMvc.perform(post("/saveEvent"))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/eventList"));
    }

    @Test
    public void form() throws Exception {

        String viewName = eventController.form();

        assertEquals("registration/eventForm", viewName);
    }

    @Test
    public void listEvents() throws Exception {

        //given
        List<Event> events = new ArrayList<>();
        events.add(new Event());
        events.add(new Event());

        when(eventService.getEvents()).thenReturn(events);

        ArgumentCaptor<List<Event>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        //when
        String viewName = eventController.listEvents(model);

        //then
        assertEquals("events", viewName);
        verify(eventService, times(1)).getEvents();
        verify(model, times(1)).addAttribute(eq("events"), argumentCaptor.capture());

        List<Event> listInController = argumentCaptor.getValue();
        assertEquals(2, listInController.size());
    }

    @Test
    public void saveEvent() throws Exception {

    }

}