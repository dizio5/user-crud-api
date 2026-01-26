package com.example.demo.dto.mapper;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.PatchUserRequest;
import com.example.demo.dto.UpdateUserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.UserEntity;

import java.time.LocalDateTime;

public class UserMapper {

    private UserMapper() {

    }

    public static UserResponse toResponse(UserEntity userEntity) {
        return new UserResponse(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getAge(),
                userEntity.getMail(),
                userEntity.getJob(),
                userEntity.getCreated()
        );
    }

    public static UserEntity toEntity(CreateUserRequest createUserRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(createUserRequest.name());
        userEntity.setSurname(createUserRequest.surname());
        userEntity.setAge(createUserRequest.age());
        userEntity.setMail(createUserRequest.mail());
        userEntity.setJob(createUserRequest.job());

        return userEntity;
    }

    public static void applyUpdate(UserEntity userEntity, UpdateUserRequest request) {
        userEntity.setName(request.name());
        userEntity.setSurname(request.surname());
        userEntity.setAge(request.age());
        userEntity.setMail(request.mail());
        userEntity.setJob(request.job());
    }

    public static void applyPatch(UserEntity userEntity, PatchUserRequest request) {
        if (request.name() != null) userEntity.setName(request.name());
        if (request.surname() != null) userEntity.setSurname(request.surname());
        if (request.age() != null) userEntity.setAge(request.age());
        if (request.mail() != null) userEntity.setMail(request.mail());
        if (request.job() != null) userEntity.setJob(request.job());
        if (request.companyId() != null) userEntity.setCompanyId(request.companyId());
    }
}
