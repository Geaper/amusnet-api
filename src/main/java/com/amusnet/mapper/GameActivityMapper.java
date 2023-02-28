package com.amusnet.mapper;

import com.amusnet.dto.GameActivityRequest;
import com.amusnet.dto.GameActivityResponse;
import com.amusnet.entity.GameActivity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameActivityMapper {

    GameActivity toEntity(GameActivityRequest request);
    GameActivityResponse toDto(GameActivity entity);
}