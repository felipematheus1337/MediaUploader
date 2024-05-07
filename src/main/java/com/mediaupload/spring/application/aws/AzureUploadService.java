package com.mediaupload.spring.application.aws;

import com.mediaupload.spring.application.cloud.CloudUploadService;
import com.mediaupload.spring.domain.model.MediaUploadDTO;
import org.springframework.stereotype.Service;


@Service
public class AzureUploadService implements CloudUploadService {
    @Override
    public void upload(MediaUploadDTO media) {

    }
}
