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
public class LectureToLectureCommand implements Converter<Lecture, LectureCommand>{

    @Synchronized
    @Nullable
    @Override
    public LectureCommand convert(Lecture lecture) {
        if (lecture == null){
            return null;
        }

        final LectureCommand lectureCommand = new LectureCommand();
        lectureCommand.setLectureId(lecture.getLectureId());
        lectureCommand.setLectureTitle(lecture.getLectureTitle());
        lectureCommand.setLectureDescription(lecture.getLectureDescription());
        return lectureCommand;
    }
}
