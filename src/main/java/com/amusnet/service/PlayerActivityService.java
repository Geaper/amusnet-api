package com.amusnet.service;

import com.amusnet.dto.PlayerActivityResponse;
import com.amusnet.entity.GameActivity;
import com.amusnet.mapper.PlayerActivityMapper;
import com.amusnet.repository.GameActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class PlayerActivityService {

    @Autowired
    private GameActivityRepository gameActivityRepository;
    @Autowired
    private PlayerActivityMapper playerActivityMapper;

    public Page<PlayerActivityResponse> getPlayerActivity(String playerId, Pageable pageable) {
        Page<GameActivity> gameActivityPage = gameActivityRepository.findByPlayerPlayerId(playerId, pageable);
        return new PageImpl<>(gameActivityPage.stream().map(playerActivityMapper::toDto).collect(Collectors.toList()));
    }
}
