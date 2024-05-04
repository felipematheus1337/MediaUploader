package com.mediaupload.spring.domain.service;

import com.mediaupload.spring.application.rest.request.UserRequest;
import com.mediaupload.spring.application.rest.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    UserResponse createUser(UserRequest user);

    UserResponse getOneUser(Long id);

    List<UserResponse> listAllUsers();

   boolean existsUserById(Long id);


}
