package com.amusnet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "games")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {

    @Id
    @Column(name = "game_id")
    private String gameId;

    @Column(name = "name")
    private String name;

    @Column(name = "pay_rate")
    private double payRate;

    @Column(name = "win_ratio")
    private double winRatio;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

}