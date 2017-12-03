package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.commands.EventCommand;
import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.exceptions.NotFoundException;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import com.eduardoportfolio.eventmanagementsystem.services.EventService;
import com.eduardoportfolio.eventmanagementsystem.services.EventServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/event/form")
    public String eventForm(Model model){
        log.debug("EventController eventForm");
        model.addAttribute("event", new EventCommand());
        return "event/eventForm";
    }

    @GetMapping("/event/{id}/show")
    public String getEventById(@PathVariable("id")Long id, Model model){
        log.debug("EventController getEventById");
        model.addAttribute("event", eventService.getEventById(id));
        return "event/showEvent";
    }

    @GetMapping("/event/{id}/update")
    public String updateEvent(@PathVariable("id")Long id, Model model){
        log.debug("EventController updateEvent");
        model.addAttribute("event", eventService.findCommandById(id));
        return "event/eventForm";
    }

    @PostMapping("/event")
    public String saveOrUpdate(@ModelAttribute EventCommand eventCommand){
        log.debug("EventController saveOrUpdate");
        EventCommand savedEvent = eventService.saveEventCommand(eventCommand);
        return "redirect:/event/"+savedEvent.getEventId()+"/show";
    }

    @GetMapping("/event/{id}/delete")
    public String deleteById(@PathVariable("id")Long id){
        log.debug("Deleting Event ID: " + id);
        eventService.deleteById(id);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error("Handling Not Found Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
