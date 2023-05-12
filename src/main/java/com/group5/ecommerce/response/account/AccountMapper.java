package com.group5.ecommerce.response.account;

import com.group5.ecommerce.entity.Account;
import com.group5.ecommerce.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "account.id", target = "id")
    @Mapping(source = "user.userName", target = "username")
    AccountResponse toResponse(Account account, User user);

    @Mapping(source = "account.id", target = "id")
    @Mapping(source = "user.userName", target = "username")
    @Mapping(source = "user.role", target = "role")
    AccountMeResponse toMeResponse(Account account, User user);

    @Mapping(source = "account.id", target = "id")
    @Mapping(source = "user.userName", target = "username")
    DetailAccountResponse toDetailResponse(Account account, User user);

}
