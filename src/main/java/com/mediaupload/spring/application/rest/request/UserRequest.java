package com.mediaupload.spring.application.rest.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private String email;

    private String phoneNumber;

    private boolean isEmailActive;

    private String name;
}
