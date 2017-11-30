package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.services.EventService;
import com.eduardoportfolio.eventmanagementsystem.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Eduardo on 30/11/17.
 */
@Slf4j
@Controller
public class ImageController {

    EventService eventService;
    ImageService imageService;

    public ImageController(EventService eventService, ImageService imageService) {
        this.eventService = eventService;
        this.imageService = imageService;
    }

    @GetMapping("event/{eventId}/image")
    public String showUploadForm(@PathVariable("eventId")Long eventId, Model model){
        log.debug("ImageController showUploadForm");
        model.addAttribute("event", eventService.findCommandById(eventId));
        return "event/imageUploadForm";
    }

    @PostMapping("event/{eventId}/image")
    public String handleImagePost(@PathVariable("eventId")Long eventId,
                                  @RequestParam("imagefile")MultipartFile multipartFile){
        log.debug("ImageController handleImagePost");
        imageService.saveImageFile(eventId, multipartFile);
        return "redirect:/event/"+eventId+"/show";
    }
}
