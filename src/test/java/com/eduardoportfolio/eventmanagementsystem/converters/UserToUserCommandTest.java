package com.eduardoportfolio.eventmanagementsystem.converters;

import com.eduardoportfolio.eventmanagementsystem.commands.UserCommand;
import com.eduardoportfolio.eventmanagementsystem.models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 19/11/17.
 */
public class UserToUserCommandTest {
    public static final Long USER_ID = 1L;
    public static final String USER_NAME = "Eduardo";
    public static final String USER_PASSWORD = "123abc";
    public static final String USER_EMAIL = "eduardo@gmail.com";
    public static final Long LECTURE_ID1 = 2L;
    public static final Long LECTURE_ID2 = 3L;
    public static final Long EVENT_ID1 = 4L;
    public static final Long EVENT_ID2 = 5L;
    UserToUserCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UserToUserCommand();
    }

    @Test
    public void testNullObjects() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObjects() throws Exception {
        assertNotNull(converter.convert(new User()));
    }

    @Test
    public void convert() throws Exception {

        //given
        User user = new User();
        user.setUserId(USER_ID);
        user.setUsername(USER_NAME);
        user.setPassword(USER_PASSWORD);
        user.setUserEmail(USER_EMAIL);

        //when
        UserCommand userCommand = converter.convert(user);

        //then
        assertNotNull(userCommand);
        assertEquals(USER_ID, userCommand.getUserId());
        assertEquals(USER_NAME, userCommand.getUsername());
        assertEquals(USER_PASSWORD, userCommand.getPassword());
        assertEquals(USER_EMAIL, userCommand.getUserEmail());
    }
}