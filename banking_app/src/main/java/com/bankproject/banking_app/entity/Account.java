package com.bankproject.banking_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="accountsDb")
@Entity
public class Account {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator = "AccountNumber")
    @SequenceGenerator(initialValue = 1000, name = "AccountNumber")

	private long id;
	
	@Column(name="accountHolderName")
	private String accountHolderName;
	
	private double balance;
	
}
