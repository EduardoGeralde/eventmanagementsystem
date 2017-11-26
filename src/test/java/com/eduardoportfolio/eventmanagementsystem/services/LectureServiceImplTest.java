package com.eduardoportfolio.eventmanagementsystem.services;

import com.eduardoportfolio.eventmanagementsystem.commands.LectureCommand;
import com.eduardoportfolio.eventmanagementsystem.converters.LectureToLectureCommand;
import com.eduardoportfolio.eventmanagementsystem.converters.UserToUserCommand;
import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import com.eduardoportfolio.eventmanagementsystem.models.Lecture;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Eduardo on 26/11/17.
 */
public class LectureServiceImplTest {

    private final LectureToLectureCommand lectureToLectureCommand;

    public LectureServiceImplTest() {
        this.lectureToLectureCommand = new LectureToLectureCommand(new UserToUserCommand());
    }

    @Mock
    EventDAO eventDAO;

    LectureService lectureService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        lectureService = new LectureServiceImpl(lectureToLectureCommand, eventDAO);
    }

    @Test
    public void findByEventIdAndLectureId() throws Exception {

    }

    @Test
    public void findByIdAndLectureIdHappyPath() throws Exception {
        //given
        Event event = new Event();
        event.setEventId(1L);

        Lecture lecture1 = new Lecture();
        lecture1.setLectureId(1L);

        Lecture lecture2 = new Lecture();
        lecture2.setLectureId(2L);

        Lecture lecture3 = new Lecture();
        lecture3.setLectureId(3L);

        event.addLecture(lecture1);
        event.addLecture(lecture2);
        event.addLecture(lecture3);
        Optional<Event> eventOptional = Optional.of(event);

        when(eventDAO.findById(anyLong())).thenReturn(eventOptional);

        //when
        LectureCommand lectureCommand =lectureService.findByEventIdAndLectureId(1L,3L);

        //then
        assertEquals(Long.valueOf(1L), lectureCommand.getEventId());
        assertEquals(Long.valueOf(3L), lectureCommand.getLectureId());
        verify(eventDAO, times(1)).findById(anyLong());
    }
}