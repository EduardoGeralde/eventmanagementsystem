package com.eduardoportfolio.eventmanagementsystem.devBootstrapData;

import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.daos.UserDAO;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import com.eduardoportfolio.eventmanagementsystem.models.Lecture;
import com.eduardoportfolio.eventmanagementsystem.models.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Eduardo on 23/10/17.
 */
@Component
public class DevBootstrapData implements ApplicationListener<ContextRefreshedEvent> {

    EventDAO eventDAO;
    UserDAO userDAO;

    public DevBootstrapData(EventDAO eventDAO, UserDAO userDAO) {
        this.eventDAO = eventDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        //get Users added from data.sql
        Optional<User> userOptional1 = userDAO.findById(1L);
        if(!userOptional1.isPresent()){
            throw new RuntimeException("Expected User Not Found");
        }

        Optional<User> userOptional2 = userDAO.findById(2L);
        if(!userOptional2.isPresent()){
            throw new RuntimeException("Expected User Not Found");
        }

        User user1 = userOptional1.get();
        User user2 = userOptional2.get();


        //Creating Lectures
        Lecture lecture1 = new Lecture();
        lecture1.setLectureTitle("Java APIs");
        lecture1.setLectureDescription("Explanation and hands on in some of the most important Java APIs ");
        lecture1.setDownVote(4L);
        lecture1.setUpVote(12L);
        lecture1.setLecturer(user1);

        Lecture lecture2 = new Lecture();
        lecture2.setLectureTitle("Java and the WEB");
        lecture2.setLectureDescription("First web concepts using Java language and platform");
        lecture2.setDownVote(10L);
        lecture2.setUpVote(2L);
        lecture2.setLecturer(user2);

        Lecture lecture3 = new Lecture();
        lecture3.setLectureTitle("Science behind the data");
        lecture3.setLectureDescription("Show the concepts and understanding about data and its powerful");
        lecture3.setDownVote(8L);
        lecture3.setUpVote(6L);
        lecture3.setLecturer(user1);

        Lecture lecture4 = new Lecture();
        lecture4.setLectureTitle("Knowing data, knowing the hidden world");
        lecture4.setLectureDescription("How we can get data, clean data, and construct strategic based on it");
        lecture4.setDownVote(6L);
        lecture4.setUpVote(8L);
        lecture4.setLecturer(user2);

        Lecture lecture5 = new Lecture();
        lecture5.setLectureTitle("Data Warehouse for BI in Industries");
        lecture5.setLectureDescription("BI for a long term memory in industries application");
        lecture4.setDownVote(8L);
        lecture4.setUpVote(1L);
        lecture5.setLecturer(user1);

        Lecture lecture6 = new Lecture();
        lecture6.setLectureTitle("BI for client satisfactions");
        lecture6.setLectureDescription("What improve, transforming data in a realistic oprations");
        lecture6.setLecturer(user2);

        //Creating Events
        Event event1 = new Event();
        event1.setEventName("MeetUp Java EE");
        event1.setEventLocal("Anhembi Centre");
        event1.setEventDate(Calendar.getInstance());
        event1.setEventDescription("Event that brought together, the amazing Java EE community");
        event1.setEventLogoPath("/users/resources/javaee.png");
        event1.setEventSite("www.javaee.com");
        event1.setEventUser(user1);
        lecture1.setLectureEvent(event1);
        lecture2.setLectureEvent(event1);
        List<Lecture> lectureList1 = new ArrayList<>();
        lectureList1.add(lecture1);
        lectureList1.add(lecture2);
        event1.setEventLectures(lectureList1);
        eventDAO.save(event1);

        Event event2 = new Event();
        event2.setEventName("Data Science Congress");
        event2.setEventLocal("Latin America Culture Space");
        event2.setEventDate(Calendar.getInstance());
        event2.setEventDescription("All Data Scientists learning together");
        event2.setEventLogoPath("/users/resources/datascience.png");
        event2.setEventSite("www.datascience.com");
        event2.setEventUser(user2);
        lecture3.setLectureEvent(event2);
        lecture4.setLectureEvent(event2);
        List<Lecture> lectureList2 = new ArrayList<>();
        lectureList2.add(lecture3);
        lectureList2.add(lecture4);
        event2.setEventLectures(lectureList2);
        eventDAO.save(event2);

        Event event3 = new Event();
        event3.setEventName("Data Warehouse for BI");
        event3.setEventLocal("Paladium Boulevard");
        event3.setEventDate(Calendar.getInstance());
        event3.setEventDescription("Let's apply all the knowledge through the BI analysis");
        event3.setEventLogoPath("/users/resources/databi.png");
        event3.setEventSite("www.datawarehouse.com");
        event3.setEventUser(user2);
        lecture5.setLectureEvent(event3);
        lecture6.setLectureEvent(event3);
        List<Lecture> lectureList3 = new ArrayList<>();
        lectureList3.add(lecture5);
        lectureList3.add(lecture6);
        event3.setEventLectures(lectureList3);
        eventDAO.save(event3);
    }
}
