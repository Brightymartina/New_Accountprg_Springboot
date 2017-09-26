package com.example.newrequestmapping;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.newrequestmapping.Account;

@Service
public class AccountServiceImp implements AccountService {

 	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

    @Override
	public Account find(Long id) {
		// TODO Auto-generated method stub
		return accountRepository.findOne(id);
	}


	@Override
	public Account save(Account a) {
		// TODO Auto-generated method stub
		return accountRepository.saveAndFlush(a);

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		accountRepository.delete(id);
	}

	 @Override
    public boolean alreadyExists(Long AccountId) {
Account account = accountRepository.findOne(AccountId);
        if (account != null) {
            return true;
        }

        return false;
    }
}
