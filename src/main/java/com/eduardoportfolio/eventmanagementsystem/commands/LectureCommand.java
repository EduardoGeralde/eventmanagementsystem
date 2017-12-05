package com.eduardoportfolio.eventmanagementsystem.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by Eduardo on 18/11/17.
 */

@Getter
@Setter
@NoArgsConstructor
public class LectureCommand {

    private Long lectureId;
    @NotBlank
    @Size(min=3, max=100)
    private String lectureTitle;
    @NotBlank
    @Size(min=3, max=255)
    private String lectureDescription;
    private UserCommand lecturer;
    private Long downVote;
    private Long upVote;
    private Long eventId;
}

