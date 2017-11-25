package com.eduardoportfolio.eventmanagementsystem.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Eduardo on 18/11/17.
 */

@Getter
@Setter
@NoArgsConstructor
public class LectureCommand {

    private Long lectureId;
    private String lectureTitle;
    private String lectureDescription;
    private UserCommand lecturer;
    private Long downVote;
    private Long upVote;
    private Long eventId;
}

