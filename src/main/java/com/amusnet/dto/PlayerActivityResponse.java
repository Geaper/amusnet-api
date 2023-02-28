package com.amusnet.dto;

import com.amusnet.model.GameOutcome;
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
public class PlayerActivityResponse {
    private String gameActivityId;
    private String gameId;
    private Double betAmount;
    private Double winAmount;
    private Currency currency;
    private GameOutcome outcome;
    private Double playerBalanceAfter;
}
