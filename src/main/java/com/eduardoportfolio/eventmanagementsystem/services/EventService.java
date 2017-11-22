package com.eduardoportfolio.eventmanagementsystem.services;

import com.eduardoportfolio.eventmanagementsystem.commands.EventCommand;
import com.eduardoportfolio.eventmanagementsystem.models.Event;

import java.util.List;

/**
 * Created by Eduardo on 15/11/17.
 */
public interface EventService {

    List<Event> getEvents();

    Event getEventById(Long id);

    EventCommand saveEventCommand(EventCommand eventCommand);

    EventCommand findCommandById (Long id);

    void deleteById(Long id);
}
