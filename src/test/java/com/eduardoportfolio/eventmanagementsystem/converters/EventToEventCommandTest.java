package com.eduardoportfolio.eventmanagementsystem.converters;

import com.eduardoportfolio.eventmanagementsystem.commands.EventCommand;
import com.eduardoportfolio.eventmanagementsystem.commands.LectureCommand;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import com.eduardoportfolio.eventmanagementsystem.models.Lecture;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 19/11/17.
 */
public class EventToEventCommandTest {
    public static final Long EVENT_ID = 1L;
    public static final String EVENT_NAME = "Event Name";
    public static final String EVENT_DESCRIPTION = "Event Description";
    public static final String EVENT_LOCAL = "Event Local";
    public static final String EVENT_SITE = "www.event.com";
    public static final String EVENT_LOGOPATH = "/user/images/logo.png";
    public static final Boolean EVENT_CLOSED = Boolean.FALSE;
    public static final Calendar EVENT_DATE = new GregorianCalendar(2013, 10,10);
    public static final Long LECTURE_ID1 = 2L;
    public static final Long LECTURE_ID2 = 3L;
    EventToEventCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new EventToEventCommand(new LectureToLectureCommand());
    }

    @Test
    public void testNullObjects() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObjects() throws Exception {
        assertNotNull(converter.convert(new Event()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Event event = new Event();
        event.setEventId(EVENT_ID);
        event.setEventName(EVENT_NAME);
        event.setEventDescription(EVENT_DESCRIPTION);
        event.setEventLocal(EVENT_LOCAL);
        event.setEventDate(EVENT_DATE);
        event.setEventSite(EVENT_SITE);
        event.setEventLogoPath(EVENT_LOGOPATH);
        event.setEventClosed(EVENT_CLOSED);

        Lecture lecture1 = new Lecture();
        lecture1.setLectureId(LECTURE_ID1);
        Lecture lecture2 = new Lecture();
        lecture2.setLectureId(LECTURE_ID2);

        event.getEventLectures().add(lecture1);
        event.getEventLectures().add(lecture2);

        //when
        EventCommand eventCommand = converter.convert(event);

        //then
        assertNotNull(eventCommand);
        assertEquals(EVENT_ID, eventCommand.getEventId());
        assertEquals(EVENT_NAME, eventCommand.getEventName());
        assertEquals(EVENT_DESCRIPTION, eventCommand.getEvenDescription());
        assertEquals(EVENT_LOCAL, eventCommand.getEventLocal());
        assertEquals(EVENT_DATE, eventCommand.getEventDate());
        assertEquals(EVENT_SITE, eventCommand.getEventSite());
        assertEquals(EVENT_LOGOPATH, eventCommand.getEventLogoPath());
        assertEquals(EVENT_CLOSED, eventCommand.getEventClosed());
        assertEquals(2, eventCommand.getEventLectures().size());
    }
}