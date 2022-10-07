/*
 * The purpose of this class is to initialise the in memory db with Account details and ATM balance.
 * In Real Life projects this class can be removed as the data can be fetched from the db on need basis.
 */

package com.zinkworks.demo.atm.configuration;

import com.zinkworks.demo.atm.model.ATM;
import com.zinkworks.demo.atm.model.Account;
import com.zinkworks.demo.atm.model.UserData;
import com.zinkworks.demo.atm.repository.ATMRepository;
import com.zinkworks.demo.atm.repository.AccountRepository;
import com.zinkworks.demo.atm.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class StartupEvents {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ATMRepository atmRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initialiseUserAccounts() {
        initAccounts();
        initATM();
        initUser();

    }

    private void initUser() {
        UserData user1Data = new UserData("56789",1234,"123456789", "abc");
        UserData user2Data = new UserData("45678",4321,"987654321", "def");

        userDataRepository.save(user1Data);
        userDataRepository.save(user2Data);
    }

    private void initAccounts() {
        Account account1=new Account("123456789",new BigDecimal(800),new BigDecimal(200),new BigDecimal(800));
        Account account2 = new Account("987654321" ,new BigDecimal(4321) ,new BigDecimal(150) ,new BigDecimal(1230));
        List<Account> accountList= new ArrayList();
        accountList.add(account1);
        accountList.add(account2);
        accountRepository.saveAll(accountList);
    }

    private void initATM() {
        ATM atm = new ATM(new BigDecimal(1500),new BigDecimal(1500),10,30,30,20);
        atmRepository.save(atm);
    }

}
