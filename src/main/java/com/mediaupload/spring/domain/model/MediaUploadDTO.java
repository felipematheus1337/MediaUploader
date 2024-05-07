package com.mediaupload.spring.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MediaUploadDTO {

    private byte[] archive;

    private MediaFormat mediaFormat;

    private StorageTypeEnum storageTypeEnum;
}
