package com.eduardoportfolio.eventmanagementsystem.services;

import com.eduardoportfolio.eventmanagementsystem.daos.EventDAO;
import com.eduardoportfolio.eventmanagementsystem.models.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Eduardo on 30/11/17.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

   private final EventDAO eventDAO;

    public ImageServiceImpl(EventDAO eventService) {
        this.eventDAO = eventService;
    }

    @Override
    public void saveImageFile(Long eventId, MultipartFile multipartFile) {
        try{
            Event event = eventDAO.findById(eventId).get();

            Byte[] byteObjects = new Byte[multipartFile.getBytes().length];

            int i = 0;
            for(byte b : multipartFile.getBytes()){
                byteObjects[i++] = b;
            }

            event.setEventLogo(byteObjects);
            eventDAO.save(event);
        } catch (IOException e){
            //todo handle better
            log.error("Error Occurred", e);
            e.printStackTrace();
        }
    }
}
