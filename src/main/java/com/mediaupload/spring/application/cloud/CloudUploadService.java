package com.mediaupload.spring.application.cloud;

import com.mediaupload.spring.domain.model.MediaUploadDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;


public interface CloudUploadService {

    void upload(MediaUploadDTO media) throws IOException;
}
