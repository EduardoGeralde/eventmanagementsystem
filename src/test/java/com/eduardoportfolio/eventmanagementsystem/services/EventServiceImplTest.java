package com.eduardoportfolio.eventmanagementsystem.services;

import com.eduardoportfolio.eventmanagementsystem.converters.EventCommandToEvent;
import com.eduardoportfolio.eventmanagementsystem.converters.EventToEventCommand;
import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.exceptions.NotFoundException;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by Eduardo on 15/11/17.
 */
public class EventServiceImplTest {

    EventServiceImpl eventService;

    @Mock
    EventDAO eventDAO;

    @Mock
    EventToEventCommand eventToEventCommand;

    @Mock
    EventCommandToEvent eventCommandToEvent;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        eventService = new EventServiceImpl(eventToEventCommand, eventCommandToEvent, eventDAO);
    }

    @Test
    public void getEventsTest() throws Exception {

        Event event = new Event();
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        when(eventService.getEvents()).thenReturn(eventList);

        List<Event> events = eventService.getEvents();

        assertEquals(events.size(), 1);
        verify(eventDAO, times(1)).findAll();
    }

    @Test
    public void getEventByIdTest() throws Exception{
        Event event = new Event();
        event.setEventId(1L);
        Optional<Event> eventOptional = Optional.of(event);

        when(eventDAO.findById(anyLong())).thenReturn(eventOptional);

        Event eventReturned = eventService.getEventById(1L);

        assertNotNull("Null Event Returned",eventReturned);
        verify(eventDAO, times(1)).findById(anyLong());
        verify(eventDAO, never()).findAll();
    }

    @Test
    public void testDeleteById() throws Exception {
        //given
        Long idToDelete = 1L;

        //when
        eventService.deleteById(idToDelete);

        //no 'when()' , since method has void return

        //then
        verify(eventDAO, times(1)).deleteById(anyLong());
    }

    @Test (expected = NotFoundException.class)
    public void testEventByIdNotFound() throws Exception {
        Optional<Event> eventOptional = Optional.empty();

        when(eventDAO.findById(anyLong())).thenReturn(eventOptional);

        Event eventReturned = eventService.getEventById(1L);

        //should go boom
    }
}