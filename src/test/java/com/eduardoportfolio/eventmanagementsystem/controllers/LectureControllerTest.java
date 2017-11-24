package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.commands.EventCommand;
import com.eduardoportfolio.eventmanagementsystem.services.EventService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Eduardo on 23/11/17.
 */
public class LectureControllerTest {

    @Mock
    EventService eventService;

    LectureController lectureController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        lectureController = new LectureController(eventService);
        mockMvc = MockMvcBuilders.standaloneSetup(lectureController).build();
    }

    @Test
    public void listLectures() throws Exception {
        //given
        EventCommand eventCommand = new EventCommand();
        when(eventService.findCommandById(anyLong())).thenReturn(eventCommand);

        //when
        mockMvc.perform(get("/event/1/lectures"))
                .andExpect(status().isOk())
                .andExpect(view().name("lecture/list"))
                .andExpect(model().attributeExists("event"));
        //then
        verify(eventService, times(1)).findCommandById(anyLong());
    }
}