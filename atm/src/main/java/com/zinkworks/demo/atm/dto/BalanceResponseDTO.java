package com.zinkworks.demo.atm.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BalanceResponseDTO {

    private String message;
    private BigDecimal currentBalance;
    private BigDecimal overdraftBalance;
    private BigDecimal maximumWithdrawalBalance;
    private String accountNumber;
}
