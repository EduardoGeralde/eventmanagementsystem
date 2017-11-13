package com.eduardoportfolio.eventmanagementsystem.daos;

import com.eduardoportfolio.eventmanagementsystem.models.Event;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Eduardo on 23/10/17.
 */
public interface EventDAO extends CrudRepository <Event, Long> {

}
