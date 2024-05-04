package com.mediaupload.spring.application.rest;


import com.mediaupload.spring.application.rest.request.UserRequest;
import com.mediaupload.spring.application.rest.response.UserResponse;
import com.mediaupload.spring.domain.model.User;
import com.mediaupload.spring.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")

public class UserController {

    private UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest user) {

        return ResponseEntity.ok(this.service.createUser(user));
    }


    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {

        return ResponseEntity.ok(this.service.listAllUsers());
    }

    @GetMapping("/userId")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") Long id) {

        return ResponseEntity.ok(this.service.getOneUser(id));
    }




}
