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
public class EventCommandToEvent implements Converter<EventCommand, Event>{

    private final LectureCommandToLecture lectureConverter;
    private final UserCommandToUser userCommandToUser;

    public EventCommandToEvent(LectureCommandToLecture lectureConverter, UserCommandToUser userCommandToUser) {
        this.lectureConverter = lectureConverter;
        this.userCommandToUser = userCommandToUser;
    }

    @Synchronized
    @Nullable
    @Override
    public Event convert(EventCommand eventCommand) {
        if(eventCommand == null){
            return null;
        }

        Event event = new Event();
        event.setEventId(eventCommand.getEventId());
        event.setEventName(eventCommand.getEventName());
        event.setEventDescription(eventCommand.getEventDescription());
        event.setEventLocal(eventCommand.getEventLocal());
        event.setEventDate(eventCommand.getEventDate());
        event.setEventSite(eventCommand.getEventSite());
        event.setEventLogoPath(eventCommand.getEventLogoPath());
        event.setEventClosed(eventCommand.getEventClosed());
        event.setEventUser(userCommandToUser.convert(eventCommand.getEventUser()));

        if (eventCommand.getEventLectures() != null && eventCommand.getEventLectures().size() > 0){
            eventCommand.getEventLectures()
                    .forEach(lecture -> event.getEventLectures()
                            .add(lectureConverter.convert(lecture)));
        }

        return event;
    }
}
