package com.eduardoportfolio.eventmanagementsystem.commands;

import com.eduardoportfolio.eventmanagementsystem.models.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 18/11/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserCommand {

    private Long userId;
    private String username;
    private String userEmail;
    private String password;
}
