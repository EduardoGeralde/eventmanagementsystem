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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lecture lecture = (Lecture) o;

        return lectureId != null ? lectureId.equals(lecture.lectureId) : lecture.lectureId == null;
    }

    @Override
    public int hashCode() {
        return lectureId != null ? lectureId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "lectureId=" + lectureId +
                ", lectureUser=" + lectureUser +
                ", lectureTitle='" + lectureTitle + '\'' +
                ", lectureDescription='" + lectureDescription + '\'' +
                ", lectureEvent=" + lectureEvent +
                '}';
    }
}
