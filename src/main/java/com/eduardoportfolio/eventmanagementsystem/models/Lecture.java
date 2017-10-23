package com.eduardoportfolio.eventmanagementsystem.models;


import javax.persistence.*;

/**
 * Created by Eduardo on 23/10/17.
 */
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;
    @ManyToOne
    private User lectureUser;
    private String lectureTitle;
    @Lob
    private String lectureDescription;
    @ManyToOne
    private Event lectureEvent;

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    public User getLectureUser() {
        return lectureUser;
    }

    public void setLectureUser(User lectureUser) {
        this.lectureUser = lectureUser;
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

    public Event getLectureEvent() {
        return lectureEvent;
    }

    public void setLectureEvent(Event lectureEvent) {
        this.lectureEvent = lectureEvent;
    }
}
