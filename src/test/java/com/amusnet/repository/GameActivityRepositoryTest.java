package com.amusnet.repository;

import com.amusnet.entity.GameActivity;
import com.amusnet.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class GameActivityRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GameActivityRepository gameActivityRepository;

    private Player player;

    private GameActivity gameActivity;

    @Before
    public void setup() {
        player = Player.builder().playerId("123").build();

        gameActivity = new GameActivity();
        gameActivity.setGameActivityId("12345");
        gameActivity.setPlayer(player);

        entityManager.persist(player);
        entityManager.persist(gameActivity);
        entityManager.flush();
    }

    @Test
    public void testExistsByGameActivityId() {
        // Test
        boolean result = gameActivityRepository.existsByGameActivityId(gameActivity.getGameActivityId());
        // Verify
        assertTrue(result);
    }

    @Test
    public void testFindByPlayerPlayerId() {
        // given
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<GameActivity> result = gameActivityRepository.findByPlayerPlayerId(player.getPlayerId(), pageable);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getNumber()).isZero();
        assertThat(result.getNumberOfElements()).isEqualTo(1);
        assertThat(result.getSize()).isEqualTo(10);
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getTotalPages()).isEqualTo(1);
    }

}

