package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.commands.EventCommand;
import com.eduardoportfolio.eventmanagementsystem.commands.LectureCommand;
import com.eduardoportfolio.eventmanagementsystem.services.EventService;
import com.eduardoportfolio.eventmanagementsystem.services.LectureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Eduardo on 23/11/17.
 */
public class LectureControllerTest {

    @Mock
    EventService eventService;
    @Mock
    LectureService lectureService;

    LectureController lectureController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        lectureController = new LectureController(eventService, lectureService);
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

    @Test
    public void testShowLecture() throws Exception {
        //given
        LectureCommand lectureCommand = new LectureCommand();

        //when
        when(lectureService.findByEventIdAndLectureId(anyLong(),anyLong())).thenReturn(lectureCommand);

        //then
        mockMvc.perform(get("/event/1/lecture/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("lecture/showLecture"))
                .andExpect(model().attributeExists("lecture"));
    }

    @Test
    public void testUpdateIngredientForm() throws Exception {
        //given
        LectureCommand lectureCommand = new LectureCommand();

        //when
        when(lectureService.findByEventIdAndLectureId(anyLong(),anyLong())).thenReturn(lectureCommand);

        //then
        mockMvc.perform(get("/event/1/lecture/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("lecture/lectureForm"))
                .andExpect(model().attributeExists("lecture"));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        //given
        LectureCommand lectureCommand = new LectureCommand();
        lectureCommand.setLectureId(3L);
        lectureCommand.setEventId(2L);

        //when
        when(lectureService.saveLectureCommand(any())).thenReturn(lectureCommand);

        //then
        mockMvc.perform(post("/event/2/lecture")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/event/2/lecture/3/show"));
    }

    @Test
    public void testDeleteLecture() throws Exception {

        mockMvc.perform(get("/event/1/lecture/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/event/1/lectures"));
        verify(lectureService,times(1)).deleteById(anyLong(),anyLong());
    }
}