package com.example.demo.dto.mapper;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.PatchUserRequest;
import com.example.demo.dto.UpdateUserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;

public class UserMapper {

    private UserMapper() {

    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getMail(),
                user.getJob(),
                user.getCreated()
        );
    }

    public static User toEntity(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setName(createUserRequest.name());
        user.setSurname(createUserRequest.surname());
        user.setAge(createUserRequest.age());
        user.setMail(createUserRequest.mail());
        user.setJob(createUserRequest.job());

        return user;
    }

    public static void applyUpdate(User user, UpdateUserRequest request) {
        user.setName(request.name());
        user.setSurname(request.surname());
        user.setAge(request.age());
        user.setMail(request.mail());
        user.setJob(request.job());
    }

    public static void applyPatch(User user, PatchUserRequest request) {
        if (request.name() != null) user.setName(request.name());
        if (request.surname() != null) user.setSurname(request.surname());
        if (request.age() != null) user.setAge(request.age());
        if (request.mail() != null) user.setMail(request.mail());
        if (request.job() != null) user.setJob(request.job());
    }
}
