package com.gudilov.lunchvotingsystem.restaurant.service.mapper;

import com.gudilov.lunchvotingsystem.restaurant.model.Restaurant;
import com.gudilov.lunchvotingsystem.restaurant.to.RestaurantTo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    void updateExistingEntity(RestaurantTo source, @MappingTarget Restaurant target);

    Restaurant transformToIntoEntity(RestaurantTo sourceTo);

    RestaurantTo transformEntityIntoViewTo(Restaurant sourceEntity);

    List<RestaurantTo> transformEntitiesIntoViewTos(List<Restaurant> sourceEntities);
}
