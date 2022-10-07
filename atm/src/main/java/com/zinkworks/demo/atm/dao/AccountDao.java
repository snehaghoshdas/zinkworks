package com.zinkworks.demo.atm.dao;

import com.zinkworks.demo.atm.model.Account;
import com.zinkworks.demo.atm.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AccountDao {

    @Autowired
    AccountRepository accountRepository;


    public Account findByAccountNumber(String userAccount) {

        return accountRepository.findByAccountNumber(userAccount);
    }


    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }
}
