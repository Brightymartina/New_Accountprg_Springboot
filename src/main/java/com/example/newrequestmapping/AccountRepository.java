package com.example.newrequestmapping;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Baraneetharan R
 *
 */
@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {



}