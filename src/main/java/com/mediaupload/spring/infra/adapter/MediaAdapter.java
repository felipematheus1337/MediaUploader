package com.mediaupload.spring.infra.adapter;


import com.mediaupload.spring.domain.model.MediaEnum;
import com.mediaupload.spring.domain.model.MediaFormat;
import com.mediaupload.spring.domain.model.MediaUploadDTO;
import com.mediaupload.spring.domain.model.StorageTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class MediaAdapter {

    public MediaUploadDTO adaptMessageToProcessToDTO(byte[] archive, MediaFormat mediaFormat) {

        return MediaUploadDTO.builder()
                .archive(archive)
                .mediaFormat(mediaFormat)
                .storageTypeEnum(mediaFormat.getType() ==
                        MediaEnum.MP3 ?
                        StorageTypeEnum.AWS :
                        StorageTypeEnum.AZURE)
                .build();
    }
}
