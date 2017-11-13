package com.eduardoportfolio.eventmanagementsystem.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

/**
 * Created by Eduardo on 23/10/17.
 */
@Entity
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
    private boolean eventClosed;
    @ManyToOne
    private User eventUser;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "lectureEvent")
    private List<Lecture> eventLectures = new ArrayList<>();

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventSite() {
        return eventSite;
    }

    public void setEventSite(String eventSite) {
        this.eventSite = eventSite;
    }

    public String getEventLocal() {
        return eventLocal;
    }

    public void setEventLocal(String eventLocal) {
        this.eventLocal = eventLocal;
    }

    public String getEventLogoPath() {
        return eventLogoPath;
    }

    public void setEventLogoPath(String eventLogoPath) {
        this.eventLogoPath = eventLogoPath;
    }

    public Calendar getEventDate() {
        return eventDate;
    }

    public void setEventDate(Calendar eventDate) {
        this.eventDate = eventDate;
    }

    public Boolean getEventActive() {
        return eventClosed;
    }

    public void setEventActive(Boolean eventActive) {
        this.eventClosed = eventActive;
    }

    public User getEventUser() {
        return eventUser;
    }

    public void setEventUser(User eventUser) {
        this.eventUser = eventUser;
    }

    public boolean isEventActive() {
        return eventClosed;
    }

    public void setEventActive(boolean eventActive) {
        this.eventClosed = eventActive;
    }

    public boolean isEventClosed() {
        return eventClosed;
    }

    public void setEventClosed(boolean eventClosed) {
        this.eventClosed = eventClosed;
    }

    public List<Lecture> getEventLectures() {
        return eventLectures;
    }

    public void setEventLectures(List<Lecture> eventLectures) {
        this.eventLectures = eventLectures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return eventId != null ? eventId.equals(event.eventId) : event.eventId == null;
    }

    @Override
    public int hashCode() {
        return eventId != null ? eventId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventSite='" + eventSite + '\'' +
                ", eventLocal='" + eventLocal + '\'' +
                ", eventLogoPath='" + eventLogoPath + '\'' +
                ", eventDate=" + eventDate +
                ", eventActive=" + eventClosed +
                ", eventUser=" + eventUser +
                '}';
    }
}
