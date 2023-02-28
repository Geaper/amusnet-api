package com.amusnet.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Currency;
import java.util.Optional;
import com.amusnet.repository.GameRepository;
import com.amusnet.repository.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.CacheManager;
import com.amusnet.dto.GameActivityRequest;
import com.amusnet.dto.GameActivityResponse;
import com.amusnet.entity.Game;
import com.amusnet.entity.GameActivity;
import com.amusnet.entity.Player;
import com.amusnet.mapper.GameActivityMapper;
import com.amusnet.model.CustomException;
import com.amusnet.model.GameOutcome;
import com.amusnet.repository.GameActivityRepository;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GameActivityServiceTest {

    @InjectMocks
    private GameActivityService gameActivityService;

    @Mock
    private GameActivityRepository gameActivityRepository;

    @Mock
    private CacheManager cacheManager;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private GameActivityMapper gameActivityMapper;

    private GameActivityRequest request;

    private GameActivity gameActivity;

    private Game game;

    private Player player;

    private GameActivityResponse response;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        request = new GameActivityRequest();
        request.setGameId("123");
        request.setPlayerId("player123");
        request.setBetAmount(10.0);
        request.setCurrency(Currency.getInstance("EUR"));
        request.setGameActivityId("gameActivity123");

        game = new Game();
        game.setGameId("123");
        game.setWinRatio(0.5);
        game.setPayRate(2.0);

        player = new Player();
        player.setPlayerId("player123");
        player.setBalance(100.0);

        gameActivity = new GameActivity();
        gameActivity.setGameActivityId("321");
        gameActivity.setGame(game);
        gameActivity.setPlayer(player);
        gameActivity.setBetAmount(10.0);
        gameActivity.setOutcome(GameOutcome.WIN);
        gameActivity.setWinAmount(30.0);
        gameActivity.setPlayerBalanceAfter(120.0);

        response = new GameActivityResponse();
        response.setGameActivityId("321");
        response.setOutcome(GameOutcome.WIN);
        response.setWinAmount(30.0);
        response.setPlayerBalanceAfter(120.0);
    }

    @Test
    public void testCreateGameActivity() throws CustomException {
        when(gameRepository.findByGameId("123")).thenReturn(Optional.of(game));
        when(playerRepository.findByPlayerId("player123")).thenReturn(Optional.of(player));
        when(gameActivityRepository.existsByGameActivityId("gameActivity123")).thenReturn(false);
        when(gameActivityRepository.save(any(GameActivity.class))).thenReturn(gameActivity);
        when(gameActivityMapper.toDto(gameActivity)).thenReturn(response);
        when(gameActivityMapper.toEntity(request)).thenReturn(gameActivity);

        GameActivityResponse response = gameActivityService.createGameActivity(request);

        assertEquals(response.getGameActivityId(), gameActivity.getGameActivityId());
    }
}
