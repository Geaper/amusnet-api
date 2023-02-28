package com.amusnet.repository;

import com.amusnet.entity.GameActivity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameActivityRepository extends JpaRepository<GameActivity, String> {

    boolean existsByGameActivityId(@NonNull String gameActivityId);

    Page<GameActivity> findByPlayerPlayerId(String playerId, Pageable pageable);
}
