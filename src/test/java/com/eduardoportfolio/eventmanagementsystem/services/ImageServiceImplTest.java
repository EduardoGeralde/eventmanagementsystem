package com.eduardoportfolio.eventmanagementsystem.services;

import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Eduardo on 30/11/17.
 */
public class ImageServiceImplTest {

    @Mock
    EventDAO eventDAO;

    ImageService imageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        imageService = new ImageServiceImpl(eventDAO);
    }

    @Test
    public void saveImageFile() throws Exception {
        //given
        Long id = 1L;
        Event event = new Event();
        event.setEventId(id);
        Optional<Event> eventOptional = Optional.of(event);

        MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt",
                "text/plain", "Spring Framework".getBytes());

        when(eventDAO.findById(anyLong())).thenReturn(eventOptional);

        ArgumentCaptor<Event>  argumentCaptor = ArgumentCaptor.forClass(Event.class);

        //when
        imageService.saveImageFile(id, multipartFile);

        //then
        verify(eventDAO, times(1)).save(argumentCaptor.capture());
        Event savedEvent = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedEvent.getEventLogo().length);
    }
}