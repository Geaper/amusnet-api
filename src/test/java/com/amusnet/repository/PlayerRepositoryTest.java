package com.amusnet.repository;

import com.amusnet.entity.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class PlayerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void testFindByPlayerId() {
        // Given
        Player player = new Player();
        player.setPlayerId("player1");
        player.setUsername("John Doe");
        entityManager.persist(player);
        entityManager.flush();

        // When
        Optional<Player> found = playerRepository.findByPlayerId("player1");

        // Then
        assertTrue(found.isPresent());
        assertEquals(player.getPlayerId(), found.get().getPlayerId());
        assertEquals(player.getUsername(), found.get().getUsername());
    }
}

