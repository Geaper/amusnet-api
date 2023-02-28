package com.amusnet;

import com.amusnet.entity.Game;
import com.amusnet.entity.Player;
import com.amusnet.repository.GameRepository;
import com.amusnet.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlayerRepository playerRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		// Add some dummy data
		Game game1 = Game.builder().gameId("45678").name("Some Slot Name 1").payRate(1.2).winRatio(0.45).build();
		Game game2 = Game.builder().gameId("45679").name("Some Slot Name 2").payRate(2).winRatio(0.33).build();

		Player player1 = Player.builder().playerId("123456789").username("tiago").balance(2000).build();

		gameRepository.saveAll(List.of(game1, game2));
		playerRepository.save(player1);
	}
}
