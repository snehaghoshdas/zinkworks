package com.zinkworks.demo.atm.controller;

import com.zinkworks.demo.atm.dto.BalanceResponseDTO;
import com.zinkworks.demo.atm.dto.IdentityRequestDTO;
import com.zinkworks.demo.atm.dto.IdentityResponseDTO;
import com.zinkworks.demo.atm.exception.InvalidUserException;
import com.zinkworks.demo.atm.model.UserData;
import com.zinkworks.demo.atm.service.AccountService;
import com.zinkworks.demo.atm.service.UserDataService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class ATMController {

    @Autowired
    private AccountService  accountService;

    @Autowired
    private UserDataService userDataService;

    @PostMapping("/get-balance")
    public ResponseEntity checkAccountBalance(@RequestBody  IdentityRequestDTO requestDTO ) {
        log.info("Validating User credentials");
        UserData userData= userDataService.validateUserDetails(requestDTO);
        log.info("Checking Balance for User {}",userData.getUserId());
        BalanceResponseDTO balance = accountService.getAccountBalance(userData.getAccountNumber());
        return ResponseEntity.ok(balance);
    }
    @PostMapping("/get-user")
    public ResponseEntity getUser(@RequestBody  IdentityRequestDTO requestDTO) {
        log.info("Validating User credentials");
        UserData userData= userDataService.validateUserDetails(requestDTO);
        return ResponseEntity.ok(IdentityResponseDTO.builder().message("Welcome to the ATM")
                .accountNumber(userData.getAccountNumber()).userId(userData.getUserId()).build());

    }
}
