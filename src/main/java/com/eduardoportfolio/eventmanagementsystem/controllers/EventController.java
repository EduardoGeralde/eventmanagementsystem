package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.commands.EventCommand;
import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import com.eduardoportfolio.eventmanagementsystem.services.EventService;
import com.eduardoportfolio.eventmanagementsystem.services.EventServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/event/form", method = RequestMethod.GET)
    public String eventForm(Model model){
        log.debug("EventController eventForm");
        model.addAttribute("event", new EventCommand());
        return "event/eventForm";
    }

    @RequestMapping(value = "/event/{id}/show")
    public String getEventById(@PathVariable("id")Long id, Model model){
        log.debug("EventController getEventById");
        model.addAttribute("event", eventService.getEventById(id));
        return "event/showEvent";
    }

    @RequestMapping(value= "/event/{id}/update")
    public String updateEvent(@PathVariable("id")Long id, Model model){
        log.debug("EventController updateEvent");
        model.addAttribute("event", eventService.findCommandById(id));
        return "event/eventForm";
    }

    @PostMapping
    @RequestMapping(value = "event")
    public String saveOrUpdate(@ModelAttribute EventCommand eventCommand){
        log.debug("EventController saveOrUpdate");
        EventCommand savedEvent = eventService.saveEventCommand(eventCommand);
        return "redirect:/event/"+savedEvent.getEventId()+"/show";
    }


}
