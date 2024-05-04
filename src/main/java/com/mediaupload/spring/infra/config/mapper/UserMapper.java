package com.mediaupload.spring.infra.config.mapper;


import com.mediaupload.spring.application.rest.request.UserRequest;
import com.mediaupload.spring.application.rest.response.UserResponse;
import com.mediaupload.spring.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;


    public User toModel(UserResponse response) {

        return mapper.map(response, User.class);
    }

    public User requestToModel(UserRequest request) {

        return mapper.map(request, User.class);
    }

    public UserResponse toResponse (User user) {

        return mapper.map(user, UserResponse.class);
    }

    public List<UserResponse> toResponseList(List<User> userList) {

        return userList.stream()
                .map(this::toResponse)
                .toList();
    }

    public List<User> toUserList(List<UserResponse> userResponseList) {

        return userResponseList.stream()
                .map(this::toModel)
                .toList();
    }




}
