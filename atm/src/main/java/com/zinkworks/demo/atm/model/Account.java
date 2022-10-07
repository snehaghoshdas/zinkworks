/*
 * This class represents Account Details of the User
 *
 *
 *
 *
 */

package com.zinkworks.demo.atm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String accountNumber;

    private BigDecimal openingBalance;
    private BigDecimal overdraft;
    private BigDecimal currentBalance;

    public Account(String accountNumber, BigDecimal openingBalance, BigDecimal overdraft, BigDecimal currentBalance) {
        this.accountNumber = accountNumber;
        this.openingBalance = openingBalance;
        this.overdraft = overdraft;
        this.currentBalance = currentBalance;
    }
}
