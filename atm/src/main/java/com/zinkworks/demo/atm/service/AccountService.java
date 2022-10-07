package com.zinkworks.demo.atm.service;

import com.zinkworks.demo.atm.dao.AccountDao;
import com.zinkworks.demo.atm.dto.BalanceResponseDTO;
import com.zinkworks.demo.atm.model.Account;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public BalanceResponseDTO getAccountBalance(String userAccount) {

        Account account = accountDao.findByAccountNumber(userAccount);
        log.info("Account balance status for {}",userAccount);
        return  BalanceResponseDTO.builder().message("Account Balance status").accountNumber(account.getAccountNumber())
                .currentBalance(account.getCurrentBalance()).overdraftBalance(account.getOverdraft()).maximumWithdrawalBalance(account.getCurrentBalance().add(account.getOverdraft())).build();
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return accountDao.findByAccountNumber(accountNumber);
    }

    public Account updateAccountBalance(Account account) {

        return accountDao.updateAccount(account);

    }
}
