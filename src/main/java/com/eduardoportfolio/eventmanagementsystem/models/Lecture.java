package com.eduardoportfolio.eventmanagementsystem.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 23/10/17.
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = "lectureEvent")
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
    private Long downVote;
    private Long upVote;
    @Lob
    @NotEmpty
    private String lectureDescription;
    @ManyToOne
    private Event lectureEvent;
}
