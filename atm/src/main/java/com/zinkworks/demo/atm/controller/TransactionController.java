package com.zinkworks.demo.atm.controller;

import com.zinkworks.demo.atm.dto.WithdrawalRequestDTO;
import com.zinkworks.demo.atm.dto.WithdrawalResponseDTO;
import com.zinkworks.demo.atm.exception.NotEnoughBalanceException;
import com.zinkworks.demo.atm.service.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

@PostMapping("/withdraw")
    public ResponseEntity<WithdrawalResponseDTO> withdraw(@RequestBody WithdrawalRequestDTO requestDTO) {
    log.info("Withdrawal Request initiated from {}",requestDTO.getAccountNumber());
    WithdrawalResponseDTO responseDTO = transactionService.withdraw(requestDTO);
    return ResponseEntity.ok(responseDTO);


}
}
