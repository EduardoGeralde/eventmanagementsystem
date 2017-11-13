package com.eduardoportfolio.eventmanagementsystem.daos;

import com.eduardoportfolio.eventmanagementsystem.models.Lecture;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Eduardo on 23/10/17.
 */
public interface LectureDAO extends CrudRepository <Lecture, Long> {

}
