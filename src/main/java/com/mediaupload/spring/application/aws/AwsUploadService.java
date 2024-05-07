package com.mediaupload.spring.application.aws;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.mediaupload.spring.application.cloud.CloudUploadService;
import com.mediaupload.spring.domain.model.MediaUploadDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AwsUploadService implements CloudUploadService {

    private final AmazonS3 amazonS3;


    @Value("${s3-bucketName}")
    private final String BUCKET_NAME;

    @Override
    public void upload(MediaUploadDTO media) throws IOException {

        log.info("Iniciando upload para Bucket S3");

        String folderName = media.getMediaFormat().getIdentificador().toString() + "/";

        String key = folderName + media.getMediaFormat().getFileName();

        File file = this.convertToFile(media.getArchive());

        if (!amazonS3.doesObjectExist(BUCKET_NAME, folderName)) amazonS3.putObject(BUCKET_NAME, folderName, file);

        ObjectMetadata metadata = new ObjectMetadata();

        metadata.setContentType("audio/mp3");
        amazonS3.putObject(new PutObjectRequest(BUCKET_NAME, key, file)
                .withMetadata(metadata));

    }

    private File convertToFile(byte[] archive) throws IOException {
        File file = File.createTempFile("temp", null);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(archive);
        }
        return file;
    }


}
