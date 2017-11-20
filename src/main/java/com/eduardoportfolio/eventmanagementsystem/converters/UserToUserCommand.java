package com.eduardoportfolio.eventmanagementsystem.converters;

import com.eduardoportfolio.eventmanagementsystem.commands.UserCommand;
import com.eduardoportfolio.eventmanagementsystem.models.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Eduardo on 18/11/17.
 */
@Component
public class UserToUserCommand implements Converter<User, UserCommand> {

    private final LectureToLectureCommand lectureConverter;
    private final EventToEventCommand eventConverter;

    public UserToUserCommand(LectureToLectureCommand lectureConverter,
                             EventToEventCommand eventConverter) {
        this.lectureConverter = lectureConverter;
        this.eventConverter = eventConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UserCommand convert(User user) {
        if(user == null){
            return null;
        }

        UserCommand userCommand = new UserCommand();
        userCommand.setUserId(user.getUserId());
        userCommand.setUserName(user.getUsername());
        userCommand.setPassword(user.getPassword());
        userCommand.setUserEmail(user.getUserEmail());

        if (user.getUserLectures() != null && user.getUserLectures().size() > 0){
            user.getUserLectures()
                    .forEach(lecture -> userCommand.getLectures().add(lectureConverter.convert(lecture)));
        }

        if (user.getUserEvents() != null && user.getUserEvents().size() > 0){
            user.getUserEvents()
                    .forEach(event -> userCommand.getEvents().add(eventConverter.convert(event)));
        }

        return userCommand;
    }
}
