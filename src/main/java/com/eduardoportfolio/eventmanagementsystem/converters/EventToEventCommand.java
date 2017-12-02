package com.eduardoportfolio.eventmanagementsystem.converters;

import com.eduardoportfolio.eventmanagementsystem.commands.EventCommand;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Eduardo on 18/11/17.
 */
@Component
public class EventToEventCommand implements Converter<Event, EventCommand> {

    private final LectureToLectureCommand lectureConverter;
    private final UserToUserCommand userToUserCommand;

    public EventToEventCommand(LectureToLectureCommand lectureConverter, UserToUserCommand userToUserCommand) {
        this.lectureConverter = lectureConverter;
        this.userToUserCommand = userToUserCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public EventCommand convert(Event event) {
        if(event == null){
            return null;
        }

        EventCommand eventCommand = new EventCommand();
        eventCommand.setEventId(event.getEventId());
        eventCommand.setEventName(event.getEventName());
        eventCommand.setEventDescription(event.getEventDescription());
        eventCommand.setEventLocal(event.getEventLocal());
        eventCommand.setEventDate(event.getEventDate());
        eventCommand.setEventSite(event.getEventSite());
        eventCommand.setEventClosed(event.getEventClosed());
        eventCommand.setEventLogo(event.getEventLogo());
        eventCommand.setEventUser(userToUserCommand.convert(event.getEventUser()));

        if (event.getEventLectures() != null && event.getEventLectures().size() > 0){
            event.getEventLectures()
                    .forEach(lecture -> eventCommand.getEventLectures()
                            .add(lectureConverter.convert(lecture)));
        }
        return eventCommand;
    }
}
