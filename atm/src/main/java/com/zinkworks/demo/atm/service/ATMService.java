package com.zinkworks.demo.atm.service;

import com.zinkworks.demo.atm.model.ATM;
import com.zinkworks.demo.atm.repository.ATMRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ATMService {
    @Autowired
    private ATMRepository atmRepository;

    public ATM getATMBalance(long id) {
        ATM atmBalance = atmRepository.findFirstById(id);
        log.info("Current ATM stats: {}", atmBalance.toString() );
        return atmBalance;
    }

    public void save(ATM atm) {
        log.info("Update Atm Balance");
        atmRepository.save(atm);
    }
}
