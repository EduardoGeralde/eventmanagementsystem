package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import com.eduardoportfolio.eventmanagementsystem.services.EventService;
import com.eduardoportfolio.eventmanagementsystem.services.EventServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Eduardo on 23/10/17.
 */
@Slf4j
@Controller
public class EventController {

    EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/eventForm", method = RequestMethod.GET)
    public String form(){
        log.debug("Getting event form");
        return "registration/eventForm";
    }

    @RequestMapping(value = "/eventList", method = RequestMethod.GET)
    public String listEvents(Model model){
        log.debug("Getting event list");
        model.addAttribute("events", eventService.getEvents());
        return "events";
    }

    @RequestMapping (value = "/saveEvent", method = RequestMethod.POST)
    public String saveEvent(Event event){
        log.debug("Saving event");
        //eventDAO.save(event);
        return "redirect:/eventList";
    }
}
