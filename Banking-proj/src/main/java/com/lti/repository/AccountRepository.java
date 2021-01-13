package com.lti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	public Account findByAccountNo(long accNo);
}
