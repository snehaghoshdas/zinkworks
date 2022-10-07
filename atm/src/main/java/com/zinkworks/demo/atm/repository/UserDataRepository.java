package com.zinkworks.demo.atm.repository;

import com.zinkworks.demo.atm.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData,Long> {
    Optional<UserData> findAccountNumberByUserId(String userId);

    Optional<UserData> findByCardNumberAndPin(String cardnumber, int pin);
}
