package com.eduardoportfolio.eventmanagementsystem.controllers;

import com.eduardoportfolio.eventmanagementsystem.daos.LectureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Eduardo on 27/10/17.
 */
@Controller
public class LectureController {

    LectureDAO lectureDAO;

    @Autowired
    public LectureController(LectureDAO lectureDAO) {
        this.lectureDAO = lectureDAO;
    }

    @RequestMapping("/lectureForm")
    public String form(){
        return "registration/lectureForm";
    }
}
