package com.amusnet.entity;

import com.amusnet.model.GameOutcome;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Currency;
import java.util.Date;

@Entity
@Table(name = "game_activity")
@Setter
@Getter
public class GameActivity {

    @Id
    @Column(name = "game_activity_id")
    private String gameActivityId;

    @Column(name = "bet_amount")
    private double betAmount;

    @Column(name = "currency")
    private Currency currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private Game game;

    @Enumerated(EnumType.STRING)
    @Column(name = "outcome")
    private GameOutcome outcome;

    @Column(name = "win_amount")
    private double winAmount;

    @Column(name = "player_balance_after")
    private double playerBalanceAfter;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

}
