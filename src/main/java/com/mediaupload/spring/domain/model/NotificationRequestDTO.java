package com.mediaupload.spring.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequestDTO {

    public String phoneNumber;

    public String identificador;

    public String email;

    public String nomeArquivo;
}
