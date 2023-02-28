package com.amusnet.service;

import com.amusnet.dto.PlayerActivityResponse;
import com.amusnet.entity.GameActivity;
import com.amusnet.mapper.PlayerActivityMapper;
import com.amusnet.repository.GameActivityRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PlayerActivityServiceTest {

    @Mock
    private GameActivityRepository gameActivityRepository;

    @Mock
    private PlayerActivityMapper playerActivityMapper;

    @InjectMocks
    private PlayerActivityService playerActivityService;

    private final String playerId = "player123";

    @Test
    public void testGetPlayerActivity() {
        // given
        Pageable pageable = PageRequest.of(0, 10);
        List<GameActivity> gameActivities = Arrays.asList(new GameActivity(), new GameActivity(), new GameActivity());
        Page<GameActivity> page = new PageImpl<>(gameActivities, pageable, gameActivities.size());
        when(gameActivityRepository.findByPlayerPlayerId(playerId, pageable)).thenReturn(page);
        when(playerActivityMapper.toDto(any(GameActivity.class))).thenReturn(new PlayerActivityResponse());

        // when
        Page<PlayerActivityResponse> result = playerActivityService.getPlayerActivity(playerId, pageable);

        // then
        assertEquals(3, result.getTotalElements());
        verify(gameActivityRepository, times(1)).findByPlayerPlayerId(playerId, pageable);
        verify(playerActivityMapper, times(3)).toDto(any(GameActivity.class));
    }

    @Test
    public void testGetPlayerActivityWithCache() {
        // given
        when(gameActivityRepository.findByPlayerPlayerId(anyString(), any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<PlayerActivityResponse> result = playerActivityService.getPlayerActivity(playerId, pageable);

        // then
        Assertions.assertNotNull(result);
    }

}


