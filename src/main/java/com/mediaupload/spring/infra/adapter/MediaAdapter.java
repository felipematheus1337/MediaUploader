package com.mediaupload.spring.infra.adapter;


import com.mediaupload.spring.domain.model.MediaFormat;
import com.mediaupload.spring.domain.model.MediaUploadDTO;
import org.springframework.stereotype.Component;

@Component
public class MediaAdapter {

    public MediaUploadDTO adaptMessageToProcessToDTO(byte[] archive, MediaFormat mediaFormat) {

        return MediaUploadDTO.builder()
                .archive(archive)
                .mediaFormat(mediaFormat)
                .build();
    }
}
