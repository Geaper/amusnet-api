package com.amusnet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Currency;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class GameActivityRequest {
    private String gameActivityId;
    private Double betAmount;
    private Currency currency;
    private String playerId;
    private String gameId;
}