package com.eduardoportfolio.eventmanagementsystem.converters;

import com.eduardoportfolio.eventmanagementsystem.commands.LectureCommand;
import com.eduardoportfolio.eventmanagementsystem.models.Lecture;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Eduardo on 19/11/17.
 */
public class LectureCommandToLectureTest {
    public static final Long LECTURE_ID = 1L;
    public static final String LECTURE_TITLE = "Lecture Title";
    public static final String LECTURE_DESCRIPTION = "Lecture Description";
    LectureCommandToLecture converter;

    @Before
    public void setUp() throws Exception {
        converter = new LectureCommandToLecture();
    }

    @Test
    public void testNullObjects() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObjects() throws Exception {
        assertNotNull(converter.convert(new LectureCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        LectureCommand lectureCommand = new LectureCommand();
        lectureCommand.setLectureId(LECTURE_ID);
        lectureCommand.setLectureTitle(LECTURE_TITLE);
        lectureCommand.setLectureDescription(LECTURE_DESCRIPTION);

        //when
        Lecture lecture = converter.convert(lectureCommand);

        //then
        assertNotNull(lecture);
        assertEquals(LECTURE_ID, lecture.getLectureId());
        assertEquals(LECTURE_TITLE, lecture.getLectureTitle());
        assertEquals(LECTURE_DESCRIPTION, lecture.getLectureDescription());
    }
}