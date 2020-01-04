package com.gudilov.lunchvotingsystem.user.service.mapper;

import com.gudilov.lunchvotingsystem.user.model.User;
import com.gudilov.lunchvotingsystem.user.to.UserCreateTo;
import com.gudilov.lunchvotingsystem.user.to.UserUpdateTo;
import com.gudilov.lunchvotingsystem.user.to.UserViewTo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    void updateExistingEntity(UserUpdateTo source, @MappingTarget User target);

    @Mapping(target = "roles", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
    User transformToIntoEntity(UserCreateTo userCreateTo);

    UserViewTo transformEntityIntoViewTo(User user);

    List<UserViewTo> transformEntitiesIntoViewTos(List<User> users);

    UserUpdateTo transformEntityIntoUpdateTo(User user);
}