package com.eduardoportfolio.eventmanagementsystem.services;

import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Eduardo on 15/11/17.
 */
@Slf4j
@Service
public class EventServiceImpl implements EventService{

    private final EventDAO eventDAO;

    public EventServiceImpl(EventDAO eventDAO) {
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
    public void saveEvent(Event event) {
        log.debug("EventServiceImpl saveEvent");
        eventDAO.save(event);
    }

    @Override
    public Event getEventById(Long id) {
        log.debug("EventServiceImpl getEventById");
        Optional<Event> eventOptional = eventDAO.findById(id);

        if(!eventOptional.isPresent()){
            throw new RuntimeException("Event Not Found");
        }
        return eventOptional.get();
    }
}
