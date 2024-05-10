package com.mediaupload.spring.infra.adapter;


import com.mediaupload.spring.domain.model.*;
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

    public NotificationRequestDTO adaptMediaToNotificationDTO(String userEmail, String phoneNumber, MediaUploadDTO mediaUploadDTO) {


        return NotificationRequestDTO.builder()
                .identificador(mediaUploadDTO.getMediaFormat().getIdentificador().toString())
                .email(userEmail)
                .phoneNumber(phoneNumber)
                .nomeArquivo(mediaUploadDTO.getMediaFormat().getFileName())
                .build();

    }
}
