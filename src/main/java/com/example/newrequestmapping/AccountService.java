package com.example.newrequestmapping;

import java.util.List;
import com.example.newrequestmapping.Account;

public interface AccountService {
    public List<Account> getAll();

    Account find(Long id);

    Account save(Account a);

    void delete(Long id);

    boolean alreadyExists(Long id);

}
