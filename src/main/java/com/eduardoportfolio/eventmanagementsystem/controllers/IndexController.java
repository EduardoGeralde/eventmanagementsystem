package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.services.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Eduardo on 16/11/17.
 */
@Slf4j
@Controller
public class IndexController {

    EventService eventService;

    @Autowired
    public IndexController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = { "", "/", "index"})
    public String getIndexPage(Model model){
        log.debug("IndexController getIndexPage");
        model.addAttribute("events", eventService.getEvents());
        return "index";
    }
}
