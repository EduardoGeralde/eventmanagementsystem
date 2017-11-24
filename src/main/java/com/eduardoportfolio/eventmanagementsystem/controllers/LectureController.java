package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.services.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Eduardo on 23/11/17.
 */
@Slf4j
@Controller
public class LectureController {

    EventService eventService;


    public LectureController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    @RequestMapping(value = "/event/{eventId}/lectures")
    public String listLectures(@PathVariable("eventId")Long eventId, Model model){
        log.debug("LectureController listLectures eventID: "+eventId);

        //use command object to avoid lazy load errors in thymeleaf
        model.addAttribute("event", eventService.findCommandById(eventId));

        return "lecture/list";
    }
}
