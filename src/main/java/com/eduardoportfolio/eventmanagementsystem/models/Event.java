package com.eduardoportfolio.eventmanagementsystem.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

/**
 * Created by Eduardo on 23/10/17.
 */
public class Event {

    private Long eventId;
    private String eventName;
    private String description;
    private String eventSite;
    private String eventLocal;
    private String eventLogoPath;
    private Calendar eventDate;
    private Boolean eventActive;
    private User user;
    private Collection<Lecture> eventLectures = new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return eventActive;
    }

    public void setEventActive(Boolean eventActive) {
        this.eventActive = eventActive;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Lecture> getEventLectures() {
        return eventLectures;
    }

    public void setEventLectures(Collection<Lecture> eventLectures) {
        this.eventLectures = eventLectures;
    }
}
