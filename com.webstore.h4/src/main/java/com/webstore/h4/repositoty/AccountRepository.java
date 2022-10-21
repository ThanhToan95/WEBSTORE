package com.webstore.h4.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webstore.h4.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	public Account findByEmail(String email);
	public Account findByResetPasswordToken(String token);
}
