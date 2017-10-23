package com.eduardoportfolio.eventmanagementsystem.models;


import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Eduardo on 23/10/17.
 */

public class User {

    private Long userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Collection<Event> userEvents = new ArrayList<>();
    private Collection<Lecture> userLectures = new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Collection<Event> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(Collection<Event> userEvents) {
        this.userEvents = userEvents;
    }

    public Collection<Lecture> getUserLectures() {
        return userLectures;
    }

    public void setUserLectures(Collection<Lecture> userLectures) {
        this.userLectures = userLectures;
    }
}
