package com.zinkworks.demo.atm.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WithdrawalRequestDTO {

    private BigDecimal requestedAmount;
    private String accountNumber;

}
