package com.eduardoportfolio.eventmanagementsystem.services;

import com.eduardoportfolio.eventmanagementsystem.commands.LectureCommand;
import com.eduardoportfolio.eventmanagementsystem.converters.LectureCommandToLecture;
import com.eduardoportfolio.eventmanagementsystem.converters.LectureToLectureCommand;
import com.eduardoportfolio.eventmanagementsystem.converters.UserCommandToUser;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Eduardo on 26/11/17.
 */
public class LectureServiceImplTest {

    private final LectureToLectureCommand lectureToLectureCommand;
    private final LectureCommandToLecture lectureCommandToLecture;

    public LectureServiceImplTest() {
        this.lectureToLectureCommand = new LectureToLectureCommand(new UserToUserCommand());
        this.lectureCommandToLecture = new LectureCommandToLecture(new UserCommandToUser());
    }

    @Mock
    EventDAO eventDAO;

    LectureService lectureService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        lectureService = new LectureServiceImpl(lectureToLectureCommand, lectureCommandToLecture, eventDAO);
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

    @Test
    public void testSaveLectureCommand() throws Exception {
        //gives
        LectureCommand lectureCommand = new LectureCommand();
        lectureCommand.setLectureId(3L);
        lectureCommand.setEventId(2L);

        Optional<Event> eventOptional = Optional.of(new Event());

        Event savedEvent = new Event();
        savedEvent.addLecture(new Lecture());
        savedEvent.getEventLectures().iterator().next().setLectureId(3L);

        //setting up mockito
        when(eventDAO.findById(anyLong())).thenReturn(eventOptional);
        when(eventDAO.save(any())).thenReturn(savedEvent);

        //when
        LectureCommand savedCommand = lectureService.saveLectureCommand(lectureCommand);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getLectureId());
        verify(eventDAO, times(1)).findById(anyLong());
        verify(eventDAO, times(1)).save(any(Event.class));
    }

    @Test
    public void testDeleteById() throws Exception {
        //given
        Event event = new Event();
        Lecture lecture = new Lecture();
        lecture.setLectureId(3L);
        event.addLecture(lecture);
        lecture.setLectureEvent(event);
        Optional<Event> eventOptional = Optional.of(event);

        when(eventDAO.findById(anyLong())).thenReturn(eventOptional);

        //when
        lectureService.deleteById(1L,3L);

        //then
        verify(eventDAO, times(1)).findById(anyLong());
        verify(eventDAO, times(1)).save(any(Event.class));
    }
}