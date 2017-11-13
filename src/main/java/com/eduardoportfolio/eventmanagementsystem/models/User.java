package com.eduardoportfolio.eventmanagementsystem.models;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.*;

/**
 * Created by Eduardo on 23/10/17.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotEmpty
    private String username;
    @NotEmpty
    @Email
    private String userEmail;
    @NotEmpty
    private String password;

    @OneToMany (mappedBy = "eventUser")
    private Set<Event> userEvents = new HashSet<>();
    @OneToMany (mappedBy = "lectureUser")
    private Set<Lecture> userLectures = new HashSet<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Event> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(Set<Event> userEvents) {
        this.userEvents = userEvents;
    }

    public Set<Lecture> getUserLectures() {
        return userLectures;
    }

    public void setUserLectures(Set<Lecture> userLectures) {
        this.userLectures = userLectures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userId != null ? userId.equals(user.userId) : user.userId == null;
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
