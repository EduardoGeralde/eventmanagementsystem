package com.eduardoportfolio.eventmanagementsystem.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

/**
 * Created by Eduardo on 23/10/17.
 */

@Data
@Entity
@Table(name = "EVENT_DETAIL")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    @NotEmpty
    private String eventName;
    @Lob
    @NotEmpty
    private String eventDescription;
    private String eventSite;
    @NotEmpty
    private String eventLocal;
    private String eventLogoPath;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Calendar eventDate;
    private Boolean eventClosed;
    @ManyToOne
    private User eventUser;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "lectureEvent")
    private List<Lecture> eventLectures = new ArrayList<>();
}
