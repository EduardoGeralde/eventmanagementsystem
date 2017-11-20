package com.eduardoportfolio.eventmanagementsystem.commands;

import com.eduardoportfolio.eventmanagementsystem.models.Lecture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Eduardo on 18/11/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class EventCommand {

    private Long eventId;
    private String eventName;
    private String evenDescription;
    private String eventSite;
    private String eventLocal;
    private String eventLogoPath;
    private Calendar eventDate;
    private Boolean eventClosed;
    private List<LectureCommand> eventLectures = new ArrayList<>();
}