package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.commands.EventCommand;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import com.eduardoportfolio.eventmanagementsystem.services.EventService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        eventController = new EventController(eventService);
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Test
    public void eventFormTest() throws Exception {

        mockMvc.perform(get("/event/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("event/eventForm"))
                .andExpect(model().attributeExists("event"));
    }

    @Test
    public void saveEventTest() throws Exception {
    //Todo implement save test
    }

    @Test
    public void getEventByIdTest() throws Exception {
        Event event = new Event();
        event.setEventId(1L);

        when(eventService.getEventById(anyLong())).thenReturn(event);

        mockMvc.perform(get("/event/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("event/showEvent"));
    }

    @Test
    public void testPostNewEventForm() throws Exception {
        EventCommand eventCommand = new EventCommand();
        eventCommand.setEventId(2L);

        when(eventService.saveEventCommand(any())).thenReturn(eventCommand);

        mockMvc.perform(post("/event").contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("eventId", "")
        .param("eventDescription", "some description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/event/2/show"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        EventCommand eventCommand = new EventCommand();
        eventCommand.setEventId(1L);

        when(eventService.findCommandById(anyLong())).thenReturn(eventCommand);

        mockMvc.perform(get("/event/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("event/eventForm"))
                .andExpect(model().attributeExists("event"));
    }
}