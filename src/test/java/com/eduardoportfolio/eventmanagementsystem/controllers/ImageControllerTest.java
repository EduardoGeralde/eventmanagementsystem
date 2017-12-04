package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.commands.EventCommand;
import com.eduardoportfolio.eventmanagementsystem.services.EventService;
import com.eduardoportfolio.eventmanagementsystem.services.ImageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Eduardo on 30/11/17.
 */
public class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    EventService eventService;

    ImageController imageController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        imageController = new ImageController(eventService,imageService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testImageForm() throws Exception {
        //given
        EventCommand eventCommand = new EventCommand();
        eventCommand.setEventId(1L);

        when(eventService.findCommandById(anyLong())).thenReturn(eventCommand);

        //when
        mockMvc.perform(get("/event/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("event"));
        verify(eventService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void testHandleImagePost() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile","testing.txt",
                "text/plain", "Spring Framework".getBytes());
        mockMvc.perform(multipart("/event/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/event/1/show"));
        verify(imageService, times(1)).saveImageFile(anyLong(),any());
    }

    @Test
    public void testRenderImageFromDB() throws Exception {
        //given
        EventCommand eventCommand = new EventCommand();
        eventCommand.setEventId(1L);

        String s = "fake image test";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;
        for (byte primeByte : s.getBytes()){
            bytesBoxed[i++] = primeByte;
        }
        eventCommand.setEventLogo(bytesBoxed);

        when(eventService.findCommandById(anyLong())).thenReturn(eventCommand);

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/event/1/eventImage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseByte = response.getContentAsByteArray();

        //then
        assertEquals(s.getBytes().length, responseByte.length);
    }

    @Test
    public void testGetRecipeNumberFormatException() throws Exception {

        mockMvc.perform(get("/event/asfd/eventImage"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

}