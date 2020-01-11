package com.gudilov.lunchvotingsystem.restaurant.service.mapper;

import com.gudilov.lunchvotingsystem.restaurant.model.MenuItem;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemTo;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemViewTo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MenuItemsMapper {

    MenuItemsMapper INSTANCE = Mappers.getMapper(MenuItemsMapper.class);

    void updateExistingEntity(MenuItemTo source, @MappingTarget MenuItem target);

    MenuItem transformToIntoEntity(MenuItemTo sourceTo);

    @Mapping(source = "restaurant.id", target = "restaurantId")
    MenuItemViewTo transformEntityIntoViewTo(MenuItem sourceEntity);

    List<MenuItemViewTo> transformEntitiesIntoViewTos(List<MenuItem> sourceEntities);
}
