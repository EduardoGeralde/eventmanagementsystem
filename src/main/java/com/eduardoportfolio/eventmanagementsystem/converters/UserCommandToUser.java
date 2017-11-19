package com.eduardoportfolio.eventmanagementsystem.converters;

import com.eduardoportfolio.eventmanagementsystem.commands.UserCommand;
import com.eduardoportfolio.eventmanagementsystem.models.Lecture;
import com.eduardoportfolio.eventmanagementsystem.models.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Eduardo on 18/11/17.
 */
@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    private final EventCommandToEvent eventConverter;
    private final LectureCommandToLecture lectureConverter;

    public UserCommandToUser(EventCommandToEvent eventConverter, LectureCommandToLecture lectureConverter) {
        this.eventConverter = eventConverter;
        this.lectureConverter = lectureConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public User convert(UserCommand userCommand) {
        if (userCommand == null) {
            return null;
        }

        final User user = new User();
        user.setUserId(userCommand.getUserId());
        user.setUsername(userCommand.getUserName());
        user.setPassword(userCommand.getPassword());
        user.setUserEmail(userCommand.getUserEmail());

        if(userCommand.getEvents() != null && userCommand.getEvents().size() > 0){
            userCommand.getEvents()
                    .forEach(event -> user.getUserEvents().add(eventConverter.convert(event)));
        }

        if(userCommand.getLectures() != null && userCommand.getLectures().size() > 0){
            userCommand.getLectures()
                    .forEach(lecture -> user.getUserLectures().add(lectureConverter.convert(lecture)));
        }

        return user;
    }


}
