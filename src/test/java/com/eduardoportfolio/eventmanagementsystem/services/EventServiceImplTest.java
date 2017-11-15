package com.eduardoportfolio.eventmanagementsystem.services;

import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Eduardo on 15/11/17.
 */
public class EventServiceImplTest {

    EventServiceImpl eventService;

    @Mock
    EventDAO eventDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        eventService = new EventServiceImpl(eventDAO);

    }

    @Test
    public void getEvents() throws Exception {

        Event event = new Event();
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        when(eventService.getEvents()).thenReturn(eventList);

        List<Event> events = eventService.getEvents();

        assertEquals(events.size(), 1);
        verify(eventDAO, times(1)).findAll();
    }

}