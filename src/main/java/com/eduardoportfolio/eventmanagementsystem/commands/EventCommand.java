package com.eduardoportfolio.eventmanagementsystem.commands;

import com.eduardoportfolio.eventmanagementsystem.models.Lecture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Lob;
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
    private String eventDescription;
    private String eventSite;
    private String eventLocal;
    private Byte[] eventLogo;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Calendar eventDate;
    private Boolean eventClosed;
    private List<LectureCommand> eventLectures = new ArrayList<>();
    private UserCommand eventUser;
}
