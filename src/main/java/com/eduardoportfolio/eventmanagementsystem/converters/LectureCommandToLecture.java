package com.eduardoportfolio.eventmanagementsystem.converters;

import com.eduardoportfolio.eventmanagementsystem.commands.LectureCommand;
import com.eduardoportfolio.eventmanagementsystem.models.Lecture;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Eduardo on 18/11/17.
 */
@Component
public class LectureCommandToLecture implements Converter<LectureCommand, Lecture>{

    @Synchronized
    @Nullable
    @Override
    public Lecture convert(LectureCommand lectureCommand) {
        if (lectureCommand == null){
            return null;
        }

        final Lecture lecture = new Lecture();
        lecture.setLectureId(lectureCommand.getLectureId());
        lecture.setLectureTitle(lectureCommand.getLectureTitle());
        lecture.setLectureDescription(lectureCommand.getLectureDescription());
        return lecture;
    }
}
