package com.bankproject.banking_app.mapper;


import com.bankproject.banking_app.dto.AccountDto;
import com.bankproject.banking_app.entity.Account;

public class AccountMapper {

	public static Account mapToAccount(AccountDto accountDto) {
		Account account =new Account(
				0, accountDto.getAccountHolderName(), accountDto.getBalance());
		return account;
	}
	public static AccountDto mapToAccountDto(Account account) {
		
		AccountDto accountDto=new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
				);
		return accountDto;
	}
		
}