package com.zinkworks.demo.atm.service;

import com.zinkworks.demo.atm.dao.UserDataDao;
import com.zinkworks.demo.atm.dto.IdentityRequestDTO;
import com.zinkworks.demo.atm.exception.IncorrectPinException;
import com.zinkworks.demo.atm.model.UserData;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Log4j2
public class UserDataService {
    @Autowired
   private UserDataDao userDataDao;

    public Optional<UserData> getAccountByUserId(String userId) {
        return userDataDao.getAccountByUserId(userId);
    }

    public UserData validateUserDetails(IdentityRequestDTO requestDTO) {
        log.info("Fetching UserDetails from Card number");
        UserData userData= userDataDao.findByCardNumberAndPin(requestDTO.getCardnumber(),Integer.parseInt(requestDTO.getPin())).orElseThrow(()-> new IncorrectPinException("Invalid credentials"));
    return userData;
    }
}
