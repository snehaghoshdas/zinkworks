package com.zinkworks.demo.atm.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WithdrawalResponseDTO {
    private String message;
    private BigDecimal withdrawalAmount;
    private BigDecimal balanceAmount;
    private Integer fiftyEuroCount;
    private Integer twentyEuroCount;
    private Integer tenEuroCount;
    private Integer fiveEuroCount;


}
