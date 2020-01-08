package com.gudilov.lunchvotingsystem.restaurant.service.mapper;

import com.gudilov.lunchvotingsystem.restaurant.model.Vote;
import com.gudilov.lunchvotingsystem.restaurant.to.VoteViewTo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface VoteMapper {

    VoteMapper INSTANCE = Mappers.getMapper(VoteMapper.class);


    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    @Mapping(source = "restaurant.name", target = "restaurantName")
    VoteViewTo transformEntityIntoViewTo(Vote sourceEntity);
}
