/*
 * Repository to transact with the ATM
 *
 */

package com.zinkworks.demo.atm.repository;

import com.zinkworks.demo.atm.model.ATM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ATMRepository extends JpaRepository<ATM,Long> {

    ATM findFirstById(long id);
}
