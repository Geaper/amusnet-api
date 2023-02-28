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
public class GameActivityResponse {
    private String gameActivityId;
    private GameOutcome outcome;
    private Double winAmount;
    private Currency currency;
    private Double playerBalanceAfter;
}
