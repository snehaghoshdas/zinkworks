/*
 * This class represents User Details that are keyed in at the ATM machine
 *
 */

package com.zinkworks.demo.atm.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user_data")
public class UserData {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String cardNumber;//Debit Card number
    private int pin;
    private String accountNumber;
    private String userId;

    public UserData(String cardNumber, int pin, String accountNumber, String userId) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.accountNumber = accountNumber;
        this.userId = userId;
    }
}
