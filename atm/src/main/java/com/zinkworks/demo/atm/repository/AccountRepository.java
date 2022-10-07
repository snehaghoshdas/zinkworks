/*
 * Repository to transact with the userAccount
 */

package com.zinkworks.demo.atm.repository;

import com.zinkworks.demo.atm.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {


    Account findByAccountNumber(String userAccount);
}
