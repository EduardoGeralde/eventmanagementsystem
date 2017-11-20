package com.eduardoportfolio.eventmanagementsystem.converters;

import com.eduardoportfolio.eventmanagementsystem.commands.EventCommand;
import com.eduardoportfolio.eventmanagementsystem.commands.LectureCommand;
import com.eduardoportfolio.eventmanagementsystem.commands.UserCommand;
import com.eduardoportfolio.eventmanagementsystem.models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 19/11/17.
 */
public class UserCommandToUserTest {
    public static final Long USER_ID = 1L;
    public static final String USER_NAME = "Eduardo";
    public static final String USER_PASSWORD = "123abc";
    public static final String USER_EMAIL = "eduardo@gmail.com";
    public static final Long LECTURE_ID1 = 2L;
    public static final Long LECTURE_ID2 = 3L;
    public static final Long EVENT_ID1 = 4L;
    public static final Long EVENT_ID2 = 5L;
    UserCommandToUser converter;

    @Before
    public void setUp() throws Exception {
        converter = new UserCommandToUser(new EventCommandToEvent(new LectureCommandToLecture()),
                new LectureCommandToLecture());
    }

    @Test
    public void testNullObjects() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObjects() throws Exception {
        assertNotNull(converter.convert(new UserCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UserCommand userCommand = new UserCommand();
        userCommand.setUserId(USER_ID);
        userCommand.setUserName(USER_NAME);
        userCommand.setPassword(USER_PASSWORD);
        userCommand.setUserEmail(USER_EMAIL);

        LectureCommand lectureCommand1 = new LectureCommand();
        lectureCommand1.setLectureId(LECTURE_ID1);
        LectureCommand lectureCommand2 = new LectureCommand();
        lectureCommand2.setLectureId(LECTURE_ID2);

        EventCommand eventCommand1 = new EventCommand();
        eventCommand1.setEventId(EVENT_ID1);
        EventCommand eventCommand2 = new EventCommand();
        eventCommand2.setEventId(EVENT_ID2);

        userCommand.getLectures().add(lectureCommand1);
        userCommand.getLectures().add(lectureCommand2);
        userCommand.getEvents().add(eventCommand1);
        userCommand.getEvents().add(eventCommand2);

        //when
        User user = converter.convert(userCommand);

        //then
        assertNotNull(user);
        assertEquals(USER_ID, user.getUserId());
        assertEquals(USER_NAME, user.getUsername());
        assertEquals(USER_PASSWORD, user.getPassword());
        assertEquals(USER_EMAIL, user.getUserEmail());
        assertEquals(2, user.getUserLectures().size());
        assertEquals(2, user.getUserEvents().size());
    }

}