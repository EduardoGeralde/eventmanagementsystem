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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Eduardo on 16/11/17.
 */
public class IndexControllerTest {

    @Mock
    EventService eventService;
    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(eventService);
    }

    @Test
    public void mockMvcTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform( get("/"))
                //Its gonna assert 200 status from our controller
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPageTest() throws Exception {
        List<Event> eventList = new ArrayList<>();
        eventList.add(new Event());
        eventList.add(new Event());

        when(eventService.getEvents()).thenReturn(eventList);

        ArgumentCaptor<List<Event>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        String viewName = indexController.getIndexPage(model);

        assertEquals("index",viewName);
        verify(eventService, times(1)).getEvents();
        verify(model,times(1)).addAttribute(eq("events"),argumentCaptor.capture());
        List<Event> listInController =argumentCaptor.getValue();
        assertEquals(2,listInController.size());
    }

}