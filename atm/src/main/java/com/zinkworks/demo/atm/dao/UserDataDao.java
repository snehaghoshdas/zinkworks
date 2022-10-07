package com.zinkworks.demo.atm.dao;

import com.zinkworks.demo.atm.model.UserData;
import com.zinkworks.demo.atm.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDataDao {
    @Autowired
    private UserDataRepository userDataRepository;


    public Optional<UserData> getAccountByUserId(String userId) {
        Optional<UserData> accountNumberByUserId = userDataRepository.findAccountNumberByUserId(userId);
        return accountNumberByUserId;
    }

    public Optional<UserData> findByCardNumberAndPin(String cardnumber, int pin) {
        return userDataRepository.findByCardNumberAndPin(cardnumber,pin);
    }
}
