package com.mediaupload.spring.domain.service;


import com.mediaupload.spring.domain.model.Media;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface MediaService {

    Media createAnMedia(MultipartFile file, Long id) throws IOException;

    void upload(Media media);






}
