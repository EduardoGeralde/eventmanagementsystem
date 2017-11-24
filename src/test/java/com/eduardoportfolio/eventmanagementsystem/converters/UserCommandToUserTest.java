package com.eduardoportfolio.eventmanagementsystem.converters;

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
        converter = new UserCommandToUser();
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
        userCommand.setUsername(USER_NAME);
        userCommand.setPassword(USER_PASSWORD);
        userCommand.setUserEmail(USER_EMAIL);

        //when
        User user = converter.convert(userCommand);

        //then
        assertNotNull(user);
        assertEquals(USER_ID, user.getUserId());
        assertEquals(USER_NAME, user.getUsername());
        assertEquals(USER_PASSWORD, user.getPassword());
        assertEquals(USER_EMAIL, user.getUserEmail());
    }

}