package com.amusnet.repository;

import com.amusnet.entity.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class GameRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void testFindByGameId() {
        Game game = new Game();
        game.setGameId("game1");
        game.setPayRate(2.0);
        game.setWinRatio(0.5);

        entityManager.persist(game);
        entityManager.flush();
        // When
        Optional<Game> found = gameRepository.findByGameId("game1");

        // Then
        assertTrue(found.isPresent());
        assertEquals(game.getGameId(), found.get().getGameId());
        assertEquals(game.getPayRate(), found.get().getPayRate(), 0.0);
        assertEquals(game.getWinRatio(), found.get().getWinRatio(), 0.0);
    }

    @Test
    public void testFindByGameIdNotFound() {
        // When
        Optional<Game> found = gameRepository.findByGameId("game1");

        // Then
        assertFalse(found.isPresent());
    }

}
