package com.group5.ecommerce.response.user;

import com.group5.ecommerce.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "avatar", source = "user.account.avatar")
    UserResponse toResponse(User user);

    List<UserResponse> toListResponse(List<User> users);

}
