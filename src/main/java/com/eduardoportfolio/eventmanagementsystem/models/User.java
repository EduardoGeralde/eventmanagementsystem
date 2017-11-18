package com.eduardoportfolio.eventmanagementsystem.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.*;

/**
 * Created by Eduardo on 23/10/17.
 */

@Data
@Entity
@Table(name="USER_DETAIL")
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

    @OneToMany (mappedBy = "eventUser", cascade = CascadeType.ALL)
    private List<Event> userEvents = new ArrayList<>();
}
