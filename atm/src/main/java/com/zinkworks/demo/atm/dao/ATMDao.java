package com.zinkworks.demo.atm.dao;

import com.zinkworks.demo.atm.repository.ATMRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ATMDao {
    @Autowired
    private ATMRepository atmRepository;
}
