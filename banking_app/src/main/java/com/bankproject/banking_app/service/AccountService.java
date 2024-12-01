package com.bankproject.banking_app.service;


import java.util.List;

import com.bankproject.banking_app.dto.AccountDto;

public interface AccountService {

	AccountDto createAccount(AccountDto accountDto);
	AccountDto getAccountByID(Long id);
	AccountDto deposit(Long id,double amount);
	AccountDto withdraw(Long id,double amount);
	List<AccountDto> getAllAccounts();
	void deleteAccount(Long id);
	AccountDto fundTransfer(Long fromId,Long toId,double amount);

}
