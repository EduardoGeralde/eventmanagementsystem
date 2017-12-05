package com.eduardoportfolio.eventmanagementsystem.commands;

import com.eduardoportfolio.eventmanagementsystem.models.Lecture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank
    @Size(min=3, max=100)
    private String eventName;
    @NotBlank
    @Size(min=3, max=255)
    private String eventDescription;
    @URL
    private String eventSite;
    @NotBlank
    @Size(min=3, max=100)
    private String eventLocal;
    private Byte[] eventLogo;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Calendar eventDate;
    private Boolean eventClosed;
    private List<LectureCommand> eventLectures = new ArrayList<>();
    private UserCommand eventUser;
}
