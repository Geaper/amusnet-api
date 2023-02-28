package com.amusnet.repository;

import com.amusnet.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {

    Optional<Game> findByGameId(String gameId);

}
