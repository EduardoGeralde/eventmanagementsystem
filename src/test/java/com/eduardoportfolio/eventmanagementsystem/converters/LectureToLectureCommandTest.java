package com.eduardoportfolio.eventmanagementsystem.converters;

import com.eduardoportfolio.eventmanagementsystem.commands.LectureCommand;
import com.eduardoportfolio.eventmanagementsystem.models.Lecture;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 19/11/17.
 */
public class LectureToLectureCommandTest {
    public static final Long LECTURE_ID = 1L;
    public static final String LECTURE_TITLE = "Lecture Title";
    public static final String LECTURE_DESCRIPTION = "Lecture Description";
    LectureToLectureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new LectureToLectureCommand(new UserToUserCommand());
    }

    @Test
    public void testNullObjects() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObjects() throws Exception {
        assertNotNull(converter.convert(new Lecture()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Lecture lecture = new Lecture();
        lecture.setLectureId(LECTURE_ID);
        lecture.setLectureTitle(LECTURE_TITLE);
        lecture.setLectureDescription(LECTURE_DESCRIPTION);

        //when
        LectureCommand lectureCommand = converter.convert(lecture);

        //then
        assertNotNull(lectureCommand);
        assertEquals(LECTURE_ID, lectureCommand.getLectureId());
        assertEquals(LECTURE_TITLE, lectureCommand.getLectureTitle());
        assertEquals(LECTURE_DESCRIPTION, lectureCommand.getLectureDescription());
    }

}