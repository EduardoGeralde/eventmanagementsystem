package com.eduardoportfolio.eventmanagementsystem.daos;

import com.eduardoportfolio.eventmanagementsystem.models.Event;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Eduardo on 23/10/17.
 */
public interface EventDAO extends CrudRepository <Event, Long> {

}
