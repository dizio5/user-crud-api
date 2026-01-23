package com.example.demo.dto.mapper;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.UserEntity;

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
}
