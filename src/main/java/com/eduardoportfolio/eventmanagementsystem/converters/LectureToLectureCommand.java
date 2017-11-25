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
    private final UserToUserCommand userToUserCommand;

    public LectureToLectureCommand(UserToUserCommand userToUserCommand) {
        this.userToUserCommand = userToUserCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public LectureCommand convert(Lecture lecture) {
        if (lecture == null){
            return null;
        }

        LectureCommand lectureCommand = new LectureCommand();
        lectureCommand.setLectureId(lecture.getLectureId());
        lectureCommand.setLectureTitle(lecture.getLectureTitle());
        lectureCommand.setLectureDescription(lecture.getLectureDescription());
        lectureCommand.setDownVote(lecture.getDownVote());
        lectureCommand.setUpVote(lecture.getUpVote());
        lectureCommand.setLecturer(userToUserCommand.convert(lecture.getLecturer()));
        return lectureCommand;
    }
}
