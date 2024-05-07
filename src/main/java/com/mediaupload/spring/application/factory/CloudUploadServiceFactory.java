package com.mediaupload.spring.application.factory;

import com.mediaupload.spring.application.aws.AwsUploadService;
import com.mediaupload.spring.application.azure.AzureUploadService;
import com.mediaupload.spring.application.cloud.CloudUploadService;
import com.mediaupload.spring.domain.model.StorageTypeEnum;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CloudUploadServiceFactory {

    private final Map<StorageTypeEnum, CloudUploadService> uploadServices = new HashMap<>();
    private final AwsUploadService awsUploadService;
    private final AzureUploadService azureUploadService;


    @PostConstruct
    private void init() {
        uploadServices.put(StorageTypeEnum.AWS, awsUploadService);
        uploadServices.put(StorageTypeEnum.AZURE, azureUploadService);

    }


    public CloudUploadService getService(StorageTypeEnum storageType) {
        return uploadServices.get(storageType);
    }
}
