package com.bankproject.banking_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankproject.banking_app.dto.AccountDto;
import com.bankproject.banking_app.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@SuppressWarnings("unused")
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody  AccountDto accountDto)
	{
		
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		AccountDto accountDto=accountService.getAccountByID(id);
		return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request) {
		
		double amount =request.get("amount");
		AccountDto accountDto=accountService.deposit(id,amount );
		return ResponseEntity.ok(accountDto);
		
	}
	   @PutMapping("/{id}/withdraw")
       public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request) {
		double amount =request.get("amount");
		AccountDto accountDto=accountService.withdraw(id,amount );
		return ResponseEntity.ok(accountDto);
		
	}
	
	   @GetMapping
		public ResponseEntity<List<AccountDto>> getAllAccounts(){
			List<AccountDto> accounts=accountService.getAllAccounts();
			return ResponseEntity.ok(accounts);
		}
	   
	   @DeleteMapping("/{id}")
	   public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Successfully");
	   }
	   
	   
	   @PutMapping("/{fromId}/fundTransfer/{toId}")
       public ResponseEntity<String> fundTransfer(@PathVariable Long fromId,@PathVariable Long toId,@RequestBody Map<String,Double> request) {
		double amount =request.get("amount");
		accountService.fundTransfer(fromId,toId,amount );
		return ResponseEntity.ok("Funds Transfer Successfull");
		
	}
	   
}
