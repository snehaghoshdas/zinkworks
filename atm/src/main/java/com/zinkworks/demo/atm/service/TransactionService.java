package com.zinkworks.demo.atm.service;

import com.zinkworks.demo.atm.dto.BalanceResponseDTO;
import com.zinkworks.demo.atm.dto.WithdrawalRequestDTO;
import com.zinkworks.demo.atm.dto.WithdrawalResponseDTO;
import com.zinkworks.demo.atm.exception.IncorrectDenominationException;
import com.zinkworks.demo.atm.exception.NotEnoughBalanceException;
import com.zinkworks.demo.atm.model.ATM;
import com.zinkworks.demo.atm.model.Account;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@Log4j2
@Transactional
public class TransactionService {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ATMService atmService;

    public WithdrawalResponseDTO withdraw(WithdrawalRequestDTO requestDTO) {
        ATM withrawnATMAmount;
        Account updatedAccount;

        //get AccountBalance
        //getATM Balance and validate
        BalanceResponseDTO accountBalance = accountService.getAccountBalance(requestDTO.getAccountNumber());
        ATM atm= atmService.getATMBalance(1);
        if(requestDTO.getRequestedAmount().compareTo(accountBalance.getMaximumWithdrawalBalance())<0
                && atm.getCurrentBalance().compareTo(requestDTO.getRequestedAmount()) >0) {
            //Subtract the Amount from ATM and update the ATM Repository
            withrawnATMAmount = updateATMRepo(atm, requestDTO.getRequestedAmount()).orElseThrow(() -> new NotEnoughBalanceException("Not Enough Balance"));

            //Subtract the amount from Account and update the Account Repo
             updatedAccount = updateAccountBalance(accountBalance,requestDTO);
        }
        else {
            log.error("Withdrawal request declined due to Insufficient Balance for {}",requestDTO.getAccountNumber());
            log.error("Requested amount {} is higher than maximum withdrawal value {}",requestDTO.getRequestedAmount(),accountBalance.getOverdraftBalance().add(accountBalance.getCurrentBalance()));
            throw new NotEnoughBalanceException("Not Enough Balance !!");
        }

return WithdrawalResponseDTO.builder().message("withdrawal Request processed...")
        .withdrawalAmount(requestDTO.getRequestedAmount())
        .balanceAmount(updatedAccount.getCurrentBalance())
        .fiftyEuroCount(withrawnATMAmount.getFiftyEuroCount())
        .twentyEuroCount(withrawnATMAmount.getTwentyEuroCount())
        .tenEuroCount(withrawnATMAmount.getTenEuroCount()).fiveEuroCount(withrawnATMAmount.getFiveEuroCount()).build();
    }

    private Account updateAccountBalance(BalanceResponseDTO accountBalance, WithdrawalRequestDTO requestDTO) {
        Account account = accountService.getAccountByAccountNumber(requestDTO.getAccountNumber());
        if (requestDTO.getRequestedAmount().compareTo(accountBalance.getCurrentBalance()) < 0) {
            account.setCurrentBalance(accountBalance.getCurrentBalance().subtract(requestDTO.getRequestedAmount()));
        account.setOverdraft(accountBalance.getOverdraftBalance());
    }
        else
        {
            account.setCurrentBalance(BigDecimal.ZERO);
            BigDecimal overdraft = accountBalance.getOverdraftBalance().subtract(requestDTO.getRequestedAmount()).compareTo(BigDecimal.ZERO)<0?BigDecimal.ZERO: accountBalance.getOverdraftBalance().subtract(requestDTO.getRequestedAmount());
            account.setOverdraft(overdraft);
        }
        log.info("Updated Account details: {}",account.toString());
        return accountService.updateAccountBalance(account);

    }

    private Optional<ATM> updateATMRepo(ATM atm, BigDecimal requestedAmount) {
        ATM withrawnATMAmount = new ATM();
        int remainingAmount = requestedAmount.intValueExact();
        log.info("Checking Atm Cash denominations...");
        //returns the dispensed cash details and save the ATM updated balance
        Integer remainingFiftyCount = atm.getFiftyEuroCount();
        Integer remainingTwentyCount = atm.getTwentyEuroCount();
        Integer remainingTenEuroCount = atm.getTenEuroCount();
        Integer remainingFiveEuroCount = atm.getFiveEuroCount();

        while (remainingAmount > 0 && (remainingAmount % 5 == 0)) {
            if (!(remainingAmount < 50) && remainingFiftyCount >0) {
                //the requested amount is equal or greater than 50
                int fiftyEuroCount =remainingAmount / 50;
                if(remainingFiftyCount < fiftyEuroCount) {

                    fiftyEuroCount= remainingFiftyCount;

                }
                withrawnATMAmount.setFiftyEuroCount(fiftyEuroCount);
                remainingFiftyCount-=fiftyEuroCount;
                remainingAmount = (remainingAmount-(fiftyEuroCount * 50))<0?0:(remainingAmount-(fiftyEuroCount * 50));
            } else if (!(remainingAmount < 20)&& remainingTwentyCount >0) {
                int twentyEuroCount = remainingAmount / 20;
                if(remainingTwentyCount< twentyEuroCount) {

                    twentyEuroCount= remainingTwentyCount;

                }
                withrawnATMAmount.setTwentyEuroCount(twentyEuroCount);
                remainingTwentyCount-=twentyEuroCount;
                remainingAmount =(remainingAmount- (twentyEuroCount * 20))<0?0:(remainingAmount- (twentyEuroCount * 20));
            } else if (!(remainingAmount < 10)&& remainingTenEuroCount >0) {
                int tenEuroCount = remainingAmount/ 10;
                if(remainingTenEuroCount<tenEuroCount) {

                    tenEuroCount= remainingTenEuroCount;

                }
                withrawnATMAmount.setTenEuroCount(tenEuroCount);
                remainingTenEuroCount-=tenEuroCount;
                remainingAmount = (remainingAmount-(tenEuroCount * 10))<0?0:(remainingAmount-(tenEuroCount * 10));
            } else if (!(remainingAmount < 5)&& remainingFiveEuroCount >0) {
                int fiveEuroCount = remainingAmount / 5;
                if(remainingFiveEuroCount <fiveEuroCount) {

                    fiveEuroCount= remainingFiveEuroCount;

                }
                withrawnATMAmount.setFiveEuroCount(fiveEuroCount);
                remainingFiveEuroCount-=fiveEuroCount;
                remainingAmount = (remainingAmount-(fiveEuroCount * 10))<0?0:(remainingAmount-(fiveEuroCount * 10));
            }

        }
        if(remainingAmount==0) {

            log.info("Cash denominations are : {}, {}, {}, {} ", withrawnATMAmount.getFiftyEuroCount(),
                    withrawnATMAmount.getTwentyEuroCount(),
                    withrawnATMAmount.getTenEuroCount(),
                    withrawnATMAmount.getFiveEuroCount());
          // int remainingFiftyNotes = remainingFiftyCount - (withrawnATMAmount.getFiftyEuroCount()!=null?withrawnATMAmount.getFiftyEuroCount():0);
           // int remainingtwentyNotes = remainingTwentyCount - (withrawnATMAmount.getTwentyEuroCount()!=null?withrawnATMAmount.getTwentyEuroCount():0);
           // int remainingTenNotes = remainingTenEuroCount - (withrawnATMAmount.getTenEuroCount()!=null?withrawnATMAmount.getTenEuroCount():0);
            //int remainingFiveNotes = remainingFiveEuroCount - (withrawnATMAmount.getFiveEuroCount()!=null?withrawnATMAmount.getFiveEuroCount():0);
            atm.setFiftyEuroCount(remainingFiftyCount);
            atm.setTwentyEuroCount(remainingTwentyCount);
            atm.setTenEuroCount(remainingTenEuroCount);
            atm.setFiveEuroCount(remainingFiveEuroCount);

            atm.setCurrentBalance(atm.getCurrentBalance().subtract(requestedAmount));
            atmService.save(atm);
            log.info("Atm status after withdrawal: {}",atm.toString());
        }
        else {
            log.error("Entered requested amount denomination is incorrect, please enter in multiple of 5");
            throw new IncorrectDenominationException("Incorrect Denomination");
        }
        return Optional.of(withrawnATMAmount);
    }
}
