package com.eduardoportfolio.eventmanagementsystem.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Eduardo on 30/11/17.
 */
public interface ImageService {

    void saveImageFile(Long recipeId, MultipartFile multipartFile);
}
