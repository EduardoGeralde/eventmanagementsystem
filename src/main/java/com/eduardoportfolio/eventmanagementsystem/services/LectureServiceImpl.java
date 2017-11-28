package com.eduardoportfolio.eventmanagementsystem.services;

import com.eduardoportfolio.eventmanagementsystem.commands.LectureCommand;
import com.eduardoportfolio.eventmanagementsystem.converters.LectureCommandToLecture;
import com.eduardoportfolio.eventmanagementsystem.converters.LectureToLectureCommand;
import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import com.eduardoportfolio.eventmanagementsystem.models.Lecture;
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
    private final LectureCommandToLecture lectureCommandToLecture;
    private final EventDAO eventDAO;

    public LectureServiceImpl(LectureToLectureCommand lectureToLectureCommand,
                              LectureCommandToLecture lectureCommandToLecture, EventDAO eventDAO) {
        this.lectureToLectureCommand = lectureToLectureCommand;
        this.lectureCommandToLecture = lectureCommandToLecture;
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
                .map(lecture -> lectureToLectureCommand.convert(lecture))
                .findFirst();
        if(!lectureCommandOptional.isPresent()){
            log.error("LectureID Not Found: " + lectureId);
            throw new RuntimeException("LectureID Not Found");
        }

        return lectureCommandOptional.get();
    }

    @Override
    public LectureCommand saveLectureCommand(LectureCommand lectureCommand) {
        log.debug("LectureServiceImpl saveLectureCommand");
        Optional<Event> eventOptional = eventDAO.findById(lectureCommand.getEventId());

        if (!eventOptional.isPresent()){
            log.error("Event Not Found, eventId: "+lectureCommand.getEventId());
            return new LectureCommand();
        } else {
            Event event = eventOptional.get();

            //Find if the Lecture already exist in Event
            Optional<Lecture> lectureOptional = event
                    .getEventLectures()
                    .stream()
                    .filter(lecture -> lecture.getLectureId().equals(lectureCommand.getLectureId()))
                    .findFirst();

            //If Lecture exists, just Update the Lecture with the received data
            if(lectureOptional.isPresent()){
                Lecture lectureFound = lectureOptional.get();
                lectureFound.setLectureTitle(lectureCommand.getLectureTitle());
                lectureFound.setLectureDescription(lectureCommand.getLectureDescription());
            } else {
                //If not, add to the Event
                Lecture lecture = lectureCommandToLecture.convert(lectureCommand);
                lecture.setLectureEvent(event);
                event.addLecture(lecture);
            }

            //Saving the Event with Lecture data changed (new or updated)
            Event savedEvent = eventDAO.save(event);

            Optional<Lecture> savedLectureOptional = savedEvent.getEventLectures()
                    .stream()
                    .filter(eventLectures -> eventLectures.getLectureId().equals(lectureCommand.getLectureId()))
                    .findFirst();

            //check by description
            if (!savedLectureOptional.isPresent()){
                //not totally safe...But best guess
                savedLectureOptional = savedEvent.getEventLectures()
                        .stream()
                        .filter(eventLectures -> eventLectures.getLectureTitle().equals(lectureCommand.getLectureTitle()))
                        .filter(eventLectures -> eventLectures.getLectureDescription().equals(lectureCommand.getLectureDescription()))
                        .findFirst();
            }

            return lectureToLectureCommand.convert(savedLectureOptional.get());
        }
    }

    @Override
    public void deleteById(Long eventId, Long lectureId) {
        log.debug("LectureServiceImpl - Deleting LectureId: "+lectureId);

        Optional<Event> eventOptional = eventDAO.findById(eventId);

        if(eventOptional.isPresent()) {
            Event event = eventOptional.get();
            log.debug("Recipe Found");

            Optional<Lecture> lectureOptional = event
                    .getEventLectures()
                    .stream()
                    .filter(lecture -> lecture.getLectureId().equals(lectureId))
                    .findFirst();

            if (lectureOptional.isPresent()) {
                Lecture lectureToDelete = lectureOptional.get();
                log.debug("Lecture Found");
                lectureToDelete.setLectureEvent(null);
                event.getEventLectures().remove(lectureOptional.get());
                eventDAO.save(event);
            } else {
                log.debug("LectureId Not Found: " + lectureId);
                throw new RuntimeException("LectureId Not Found");
            }
        } else {
            log.debug("EventId Not Found: " + eventId);
            throw new RuntimeException("EventId Not Found");
        }
    }
}
