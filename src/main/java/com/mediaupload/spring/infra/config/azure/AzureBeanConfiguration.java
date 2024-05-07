package com.mediaupload.spring.infra.config.azure;


import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AzureBeanConfiguration {


    @Value("${azure-accountName}")
    private String accountName;

    @Value("${azure-containerName}")
    private String containerName;

    public DefaultAzureCredential defaultAzureCredential;
    public  BlobServiceClient blobServiceClient;
    public  BlobContainerClient blobContainerClient;


    @Bean
    public DefaultAzureCredential defaultAzureCredential() {
        defaultAzureCredential = new DefaultAzureCredentialBuilder().build();
        return defaultAzureCredential;
    }

    @Bean
    public BlobServiceClient blobServiceClient() {
         blobServiceClient = new BlobServiceClientBuilder()
                .endpoint(accountName)
                .credential(defaultAzureCredential)
                .buildClient();

         return blobServiceClient;
    }

    @Bean
    public BlobContainerClient blobContainerClient () {

        return blobServiceClient.createBlobContainer(containerName);
    }

}
