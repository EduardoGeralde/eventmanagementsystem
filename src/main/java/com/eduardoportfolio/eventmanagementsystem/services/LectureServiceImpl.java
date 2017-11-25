package com.eduardoportfolio.eventmanagementsystem.services;

import com.eduardoportfolio.eventmanagementsystem.commands.LectureCommand;
import com.eduardoportfolio.eventmanagementsystem.converters.LectureToLectureCommand;
import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Eduardo on 24/11/17.
 */
@Slf4j
@Service
public class LectureServiceImpl implements LectureService {

    private final LectureToLectureCommand lectureToLectureCommand;
    private final EventDAO eventDAO;

    public LectureServiceImpl(LectureToLectureCommand lectureToLectureCommand, EventDAO eventDAO) {
        this.lectureToLectureCommand = lectureToLectureCommand;
        this.eventDAO = eventDAO;
    }

    @Override
    public LectureCommand findByEventIdAndLectureId(Long eventId, Long lectureId) {
        log.debug("LectureServiceImpl findByEventIdAndLectureId");

        Optional<Event> eventOptional = eventDAO.findById(eventId);
        if(!eventOptional.isPresent()){
            log.error("EventID Not Found: "+eventId);
            throw new RuntimeException("EventId Not Found");
        }

        Event event = eventOptional.get();

        Optional<LectureCommand> lectureCommandOptional = event.getEventLectures().stream()
                .filter(lecture -> lecture.getLectureId().equals(lectureId))
                .map(lecture -> lectureToLectureCommand.convert(lecture)).findFirst();
        if(!lectureCommandOptional.isPresent()){
            log.error("LectureID Not Found: " + lectureId);
            throw new RuntimeException("LectureID Not Found");
        }

        return lectureCommandOptional.get();
    }
}
