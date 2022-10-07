/*
 * Copyright (c) 2022. Model representing the ATM Machine
 */

package com.zinkworks.demo.atm.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ATM")
public class ATM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal openingBalance;
    private BigDecimal currentBalance;
    private Integer fiftyEuroCount;
    private Integer twentyEuroCount;
    private Integer tenEuroCount;
    private Integer fiveEuroCount;

    public ATM(BigDecimal openingBalance, BigDecimal currentBalance,
               Integer fiftyEuroCount, Integer twentyEuroCount, Integer tenEuroCount,
               Integer fiveEuroCount) {
        this.openingBalance = openingBalance;
        this.currentBalance = currentBalance;
        this.fiftyEuroCount = fiftyEuroCount;
        this.twentyEuroCount = twentyEuroCount;
        this.tenEuroCount = tenEuroCount;
        this.fiveEuroCount = fiveEuroCount;

    }
}
