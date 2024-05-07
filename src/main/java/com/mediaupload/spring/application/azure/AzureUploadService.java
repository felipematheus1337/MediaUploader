package com.mediaupload.spring.application.azure;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.mediaupload.spring.application.cloud.CloudUploadService;
import com.mediaupload.spring.domain.model.MediaUploadDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


@Service
@RequiredArgsConstructor
@Slf4j
public class AzureUploadService implements CloudUploadService {


    private final BlobContainerClient azureContainerClient;

    @Override
    public void upload(MediaUploadDTO media) {

        log.info("Realizando upload na Azure.");

        String fileName = media.getMediaFormat().getFileName();

        BlobClient blobClient = azureContainerClient.getBlobClient(fileName);

        InputStream fileInputStream = new ByteArrayInputStream(media.getArchive());

        blobClient.upload(fileInputStream);



    }
}
