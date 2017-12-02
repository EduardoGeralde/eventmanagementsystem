package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.commands.EventCommand;
import com.eduardoportfolio.eventmanagementsystem.services.EventService;
import com.eduardoportfolio.eventmanagementsystem.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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

    @GetMapping("event/{eventId}/eventImage")
    public void renderImageFromDB(@PathVariable("eventId")Long eventId, HttpServletResponse response) throws IOException{
        log.debug("ImageController renderImageFromDB");
        EventCommand eventCommand = eventService.findCommandById(eventId);

        if (eventCommand != null) {
            byte[] byteArray = new byte[eventCommand.getEventLogo().length];

            int i = 0;
            for (Byte wrappedByte : eventCommand.getEventLogo()) {
                byteArray[i++] = wrappedByte;  //auto unboxing
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
