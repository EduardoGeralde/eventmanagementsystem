package com.eduardoportfolio.eventmanagementsystem.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Created by Eduardo on 23/10/17.
 */
@Data
@Entity
@Table(name="LECTURE_DETAIL")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;
    @ManyToOne
    private User lecturer;
    @NotEmpty
    private String lectureTitle;
    @Lob
    @NotEmpty
    private String lectureDescription;
    @ManyToOne
    private Event lectureEvent;
}
