package com.bankproject.banking_app.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.bankproject.banking_app.dto.AccountDto;
import com.bankproject.banking_app.entity.Account;
import com.bankproject.banking_app.mapper.AccountMapper;
import com.bankproject.banking_app.repository.AccountRepository;
import com.bankproject.banking_app.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository accountRepository;

	private AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository=accountRepository;
	}
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedAccount=accountRepository.save(account);	
		return AccountMapper.mapToAccountDto(savedAccount);
	}
	
	@Override
	public AccountDto getAccountByID(Long id) {
		Account account  = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("AccountNotFound"));
		return AccountMapper.mapToAccountDto(account);
	}
	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account  = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("AccountNotFound"));
	 double total =  account.getBalance()+amount;
	 account.setBalance(total);
	 Account savedAccount= accountRepository.save(account);
	 return AccountMapper.mapToAccountDto(savedAccount);
			
	}
	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account  = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("AccountNotFound"));
	if(account.getBalance() < amount) {
		throw new RuntimeException("Insufficient Amount");
	}
	 double balance=  account.getBalance()- amount;
	 account.setBalance(balance);
	 Account savedAccount= accountRepository.save(account);
	 return AccountMapper.mapToAccountDto(savedAccount);
	}
	@Override
	public List<AccountDto> getAllAccounts() {
	List<Account> accounts = accountRepository.findAll();
	return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
	
	}
	@Override
	public void deleteAccount(Long id) {
		Account account  = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("AccountNotFound"));
		accountRepository.deleteById(id);
	}
	
	@Override
	
	public AccountDto fundTransfer(Long fromId, Long toId, double amount) {
		Account account1  = accountRepository.findById(fromId).orElseThrow(() -> new RuntimeException("AccountNotFound"));
		Account account2=	accountRepository.findById(toId).orElseThrow(() -> new RuntimeException("ToAccountNotFound"));
		if(account1.getBalance() < amount) {
			throw new RuntimeException("Insufficient Amount");
		}
		account1.setBalance(account1.getBalance()-amount);
		account2.setBalance(account2.getBalance()+amount);
		Account savedAccount1= accountRepository.save(account1);
		accountRepository.save(account2);
		return AccountMapper.mapToAccountDto(savedAccount1);
	}

}
