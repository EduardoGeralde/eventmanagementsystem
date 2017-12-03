package com.eduardoportfolio.eventmanagementsystem.services;

import com.eduardoportfolio.eventmanagementsystem.commands.EventCommand;
import com.eduardoportfolio.eventmanagementsystem.converters.EventCommandToEvent;
import com.eduardoportfolio.eventmanagementsystem.converters.EventToEventCommand;
import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.exceptions.NotFoundException;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Eduardo on 15/11/17.
 */
@Slf4j
@Service
public class EventServiceImpl implements EventService{

    private final EventToEventCommand eventToEventCommand;
    private final EventCommandToEvent eventCommandToEvent;
    private final EventDAO eventDAO;

    public EventServiceImpl(EventToEventCommand eventToEventCommand,
                            EventCommandToEvent eventCommandToEvent, EventDAO eventDAO) {
        this.eventToEventCommand = eventToEventCommand;
        this.eventCommandToEvent = eventCommandToEvent;
        this.eventDAO = eventDAO;
    }

    @Override
    public List<Event> getEvents() {
        log.debug("EventServiceImpl getEvents");
        List<Event> eventList = new ArrayList<>();
        eventDAO.findAll().iterator().forEachRemaining(eventList::add);
        return eventList;
    }

    @Override
    public Event getEventById(Long id) {
        log.debug("EventServiceImpl getEventById");
        Optional<Event> eventOptional = eventDAO.findById(id);

        if(!eventOptional.isPresent()){
            throw new NotFoundException("Event Not Found!!");
        }
        return eventOptional.get();
    }

    @Transactional
    @Override
    public EventCommand saveEventCommand(EventCommand eventCommand) {
        Event detachedEvent = eventCommandToEvent.convert(eventCommand);
        Event savedEvent = eventDAO.save(detachedEvent);
        log.debug("Saved EventID: "+savedEvent.getEventId());
        return eventToEventCommand.convert(savedEvent);
    }

    @Transactional
    @Override
    public EventCommand findCommandById(Long id) {
        return eventToEventCommand.convert(getEventById(id));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("EventServiceImpl deleteById");
        eventDAO.deleteById(id);
    }
}
