package com.amusnet.service;

import com.amusnet.dto.GameActivityRequest;
import com.amusnet.dto.GameActivityResponse;
import com.amusnet.entity.Game;
import com.amusnet.entity.Player;
import com.amusnet.model.CustomException;
import com.amusnet.model.GameOutcome;
import com.amusnet.mapper.GameActivityMapper;
import com.amusnet.entity.GameActivity;
import com.amusnet.repository.GameActivityRepository;
import com.amusnet.repository.GameRepository;
import com.amusnet.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class GameActivityService {

    @Autowired
    private GameActivityRepository gameActivityRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameActivityMapper gameActivityMapper;
    @Autowired
    private CacheManager cacheManager;

    public GameActivityResponse createGameActivity(GameActivityRequest request) throws CustomException {
        // Get the associated game
        Optional<Game> gameOpt = gameRepository.findByGameId(request.getGameId());
        // Game does not exist
        if(gameOpt.isEmpty()) {
            throw new CustomException(String.format("Game %s does not exist", request.getGameId()));
        }
        // Get the associated player
        Optional<Player> playerOpt = playerRepository.findByPlayerId(request.getPlayerId());
        // Player does not exist
        if(playerOpt.isEmpty()) {
            throw new CustomException(String.format("Player %s does not exist", request.getGameId()));
        }
        // Not enough money
        if(playerOpt.get().getBalance() - request.getBetAmount() < 0) {
            throw new CustomException(String.format("The bet %.2f %s exceeds your balance of %.2f %s", request.getBetAmount(), request.getCurrency(), playerOpt.get().getBalance(), request.getCurrency()));
        }
        // Check if this bet has already been recorded
        if(gameActivityRepository.existsByGameActivityId(request.getGameActivityId())) {
            throw new CustomException("This bet was already placed");
        }

        GameActivity gameActivity = gameActivityMapper.toEntity(request);
        gameActivity.setGame(gameOpt.get());
        gameActivity.setPlayer(playerOpt.get());
        gameActivity.setOutcome(getRandomOutcome(gameActivity.getGame()));
        gameActivity.setWinAmount(gameActivity.getOutcome() == GameOutcome.WIN ? (calculateWinAmount(request.getBetAmount(), gameOpt.get())) : 0);
        gameActivity.getPlayer().setBalance(gameActivity.getPlayer().getBalance() - gameActivity.getBetAmount() + gameActivity.getWinAmount());
        gameActivity.setPlayerBalanceAfter(Math.round(gameActivity.getPlayer().getBalance() * 100) / 100.0);
        // Save to DB
        gameActivity = gameActivityRepository.save(gameActivity);
        // Set response
        return gameActivityMapper.toDto(gameActivity);
    }

    private GameOutcome getRandomOutcome(Game game) {
        return Math.random() < game.getWinRatio() ? GameOutcome.WIN : GameOutcome.LOSS;
    }

    private double calculateWinAmount(double betAmount, Game game) {
        return betAmount + (game.getPayRate() * betAmount);
    }
}
