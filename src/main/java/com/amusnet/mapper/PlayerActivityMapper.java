package com.amusnet.mapper;

import com.amusnet.dto.PlayerActivityResponse;
import com.amusnet.entity.GameActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerActivityMapper {

    @Mapping(target = "gameId", source = "game.gameId")
    PlayerActivityResponse toDto(GameActivity entity);
}