package com.mediaupload.spring.impl.domain.service;

import com.mediaupload.spring.application.rest.request.UserRequest;
import com.mediaupload.spring.application.rest.response.UserResponse;
import com.mediaupload.spring.domain.exceptions.UserNotFoundException;
import com.mediaupload.spring.domain.model.User;
import com.mediaupload.spring.domain.repository.UserRepository;
import com.mediaupload.spring.domain.service.UserService;
import com.mediaupload.spring.infra.config.UserMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = mapper.requestToModel(request);

        var userCreated = this.userRepository.save(user);

        return mapper.toResponse(userCreated);

    }

    public boolean existsUserById(Long id) {

        return this.userRepository.existsById(id);
    }

    @Override
    public UserResponse getOneUser(Long id) {

        if (existsUserById(id)) {
            var userFound = this.userRepository.getReferenceById(id);

            return mapper.toResponse(userFound);
        }

        throw new UserNotFoundException("User not found with id : " + id);

    }

    @Override
    public List<UserResponse> listAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
