package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Eduardo on 23/10/17.
 */
@Controller
public class EventController {

    EventDAO eventDAO;

    @Autowired
    public EventController(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @RequestMapping(value = "/eventForm")
    public String form(){
        return "registration/eventForm";
    }

    @RequestMapping(value = "/listEvents", method = RequestMethod.GET)
    public String listEvents(Model model){
        model.addAttribute("events", eventDAO.findAll());
        return "events";
    }

    @RequestMapping (value = "/saveEvent", method = RequestMethod.POST)
    public String saveEvent(Event event){
        eventDAO.save(event);
        return "redirect:listEvents";
    }
}
