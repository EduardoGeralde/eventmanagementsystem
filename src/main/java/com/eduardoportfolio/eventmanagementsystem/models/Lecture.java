package com.eduardoportfolio.eventmanagementsystem.models;


/**
 * Created by Eduardo on 23/10/17.
 */
public class Lecture {

    private Long lectureId;
    private User userLecturer;
    private String lectureTitle;
    private String lectureDescription;
    private Event event;

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    public User getUserLecturer() {
        return userLecturer;
    }

    public void setUserLecturer(User userLecturer) {
        this.userLecturer = userLecturer;
    }

    public String getLectureTitle() {
        return lectureTitle;
    }

    public void setLectureTitle(String lectureTitle) {
        this.lectureTitle = lectureTitle;
    }

    public String getLectureDescription() {
        return lectureDescription;
    }

    public void setLectureDescription(String lectureDescription) {
        this.lectureDescription = lectureDescription;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
