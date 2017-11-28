package com.eduardoportfolio.eventmanagementsystem.services;

import com.eduardoportfolio.eventmanagementsystem.commands.LectureCommand;

/**
 * Created by Eduardo on 24/11/17.
 */
public interface LectureService {
    LectureCommand findByEventIdAndLectureId(Long eventId, Long lectureId);
    LectureCommand saveLectureCommand(LectureCommand lectureCommand);
    void deleteById(Long eventId, Long lectureId);
}
