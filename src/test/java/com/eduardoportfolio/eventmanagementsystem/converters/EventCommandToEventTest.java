package com.eduardoportfolio.eventmanagementsystem.converters;

import com.eduardoportfolio.eventmanagementsystem.commands.EventCommand;
import com.eduardoportfolio.eventmanagementsystem.commands.LectureCommand;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 19/11/17.
 */
public class EventCommandToEventTest {
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
    EventCommandToEvent converter;

    @Before
    public void setUp() throws Exception {
        converter = new EventCommandToEvent(new LectureCommandToLecture(new UserCommandToUser()), new UserCommandToUser());
    }

    @Test
    public void testNullObjects() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObjects() throws Exception {
        assertNotNull(converter.convert(new EventCommand()));
    }

    @Test
    public void convert() throws Exception {

        //given
        EventCommand eventCommand = new EventCommand();
        eventCommand.setEventId(EVENT_ID);
        eventCommand.setEventName(EVENT_NAME);
        eventCommand.setEventDescription(EVENT_DESCRIPTION);
        eventCommand.setEventLocal(EVENT_LOCAL);
        eventCommand.setEventDate(EVENT_DATE);
        eventCommand.setEventSite(EVENT_SITE);
        eventCommand.setEventLogoPath(EVENT_LOGOPATH);
        eventCommand.setEventClosed(EVENT_CLOSED);

        LectureCommand lectureCommand1 = new LectureCommand();
        lectureCommand1.setLectureId(LECTURE_ID1);
        LectureCommand lectureCommand2 = new LectureCommand();
        lectureCommand2.setLectureId(LECTURE_ID2);

        eventCommand.getEventLectures().add(lectureCommand1);
        eventCommand.getEventLectures().add(lectureCommand2);

        //when
        Event event = converter.convert(eventCommand);

        //then
        assertNotNull(event);
        assertEquals(EVENT_ID, event.getEventId());
        assertEquals(EVENT_NAME, event.getEventName());
        assertEquals(EVENT_DESCRIPTION, event.getEventDescription());
        assertEquals(EVENT_LOCAL, event.getEventLocal());
        assertEquals(EVENT_DATE, event.getEventDate());
        assertEquals(EVENT_SITE, event.getEventSite());
        assertEquals(EVENT_LOGOPATH, event.getEventLogoPath());
        assertEquals(EVENT_CLOSED, event.getEventClosed());
        assertEquals(2, event.getEventLectures().size());
    }

}