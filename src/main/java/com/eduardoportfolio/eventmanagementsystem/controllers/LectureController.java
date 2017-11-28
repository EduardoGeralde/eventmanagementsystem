package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.commands.LectureCommand;
import com.eduardoportfolio.eventmanagementsystem.models.Lecture;
import com.eduardoportfolio.eventmanagementsystem.services.EventService;
import com.eduardoportfolio.eventmanagementsystem.services.LectureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Eduardo on 23/11/17.
 */
@Slf4j
@Controller
public class LectureController {

    EventService eventService;
    LectureService lectureService;

    public LectureController(EventService eventService, LectureService lectureService) {
        this.eventService = eventService;
        this.lectureService = lectureService;
    }

    @GetMapping
    @RequestMapping(value = "/event/{eventId}/lectures")
    public String listLectures(@PathVariable("eventId")Long eventId, Model model){
        log.debug("LectureController listLectures eventID: "+eventId);

        //use command object to avoid lazy load errors in thymeleaf
        model.addAttribute("event", eventService.findCommandById(eventId));
        return "lecture/list";
    }

    @GetMapping
    @RequestMapping(value="/event/{eventId}/lecture/{lectureId}/show")
    public String showLecture(@PathVariable("eventId") Long eventId,
                              @PathVariable("lectureId") Long lectureId, Model model){
        log.debug("LectureController showLecture");

        //use command object to avoid lazy load errors in thymeleaf
        model.addAttribute("lecture", lectureService.findByEventIdAndLectureId(eventId,lectureId));
        return "lecture/showLecture";
    }

    @GetMapping
    @RequestMapping("/event/{eventId}/lecture/{lectureId}/update")
    public String updateEventLecture(@PathVariable("eventId") Long eventId,
                                     @PathVariable("lectureId")Long lectureId, Model model){
        log.debug("LectureController updateEventLecture");

        model.addAttribute("lecture",lectureService.findByEventIdAndLectureId(eventId,lectureId));
        return "lecture/lectureForm";
    }

    @PostMapping
    @RequestMapping("/event/{eventId}/lecture")
    public String saveOrUpdate(@ModelAttribute LectureCommand lectureCommand){
        LectureCommand savedCommand = lectureService.saveLectureCommand(lectureCommand);
        log.debug("Saved EventId: "+savedCommand.getEventId());
        log.debug("Saved LectureId: "+savedCommand.getLectureId());
        return "redirect:/event/"+savedCommand.getEventId()+"/lecture/"+savedCommand.getLectureId()+"/show";
    }
}
