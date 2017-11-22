package com.eduardoportfolio.eventmanagementsystem.services;

import com.eduardoportfolio.eventmanagementsystem.commands.EventCommand;
import com.eduardoportfolio.eventmanagementsystem.converters.EventCommandToEvent;
import com.eduardoportfolio.eventmanagementsystem.converters.EventToEventCommand;
import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 20/11/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceIT {


    public static final String EVENT_DESCRIPTION = "Event Description";

    @Autowired
    EventService eventService;

    @Autowired
    EventDAO eventDAO;

    @Autowired
    EventToEventCommand eventToEventCommand;

    @Autowired
    EventCommandToEvent eventCommandToEvent;

    @Transactional
    @Test
    public void saveEventCommand() throws Exception {

        //given
        Iterable<Event> events = eventDAO.findAll();
        Event testEvent = events.iterator().next();
        EventCommand testEventCommand = eventToEventCommand.convert(testEvent);

        //when
        testEventCommand.setEventDescription(EVENT_DESCRIPTION);
        EventCommand savedEvent = eventService.saveEventCommand(testEventCommand);

        //then
        assertEquals(EVENT_DESCRIPTION, savedEvent.getEventDescription());
        assertEquals(testEvent.getEventId(), savedEvent.getEventId());
        assertEquals(testEvent.getEventLectures().size(), savedEvent.getEventLectures().size());
    }

}