package com.webstore.h4.service;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webstore.h4.dto.UserDto;
import com.webstore.h4.entity.Account;
import com.webstore.h4.repositoty.AccountRepository;

@Service
@Transactional
public class UserService implements UserDetailsService{
	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	BCryptPasswordEncoder pe;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Account ac = accountRepo.findById(username).get();
			String password = ac.getPassword();
			String[] roles = ac.getAuth().stream()
							.map(au->au.getRole().getId())
							.collect(Collectors.toList()).toArray(new String[0]);
			return User.withUsername(username).password(pe.encode(password)).roles(roles).build();
		}catch(Exception e) {
			throw new UsernameNotFoundException("Not Found "+ username );
		}
		
	}
	public void saveUser(UserDto userDto) {
		  Account ac = new Account();
	        ac.setUsername(userDto.getUsername());
	        ac.setFullname(userDto.getFullname());
	        ac.setPassword(userDto.getPassword());
	        ac.setEmail(userDto.getEmail());
	        ac.setphone(userDto.getPhone());
	         accountRepo.save(ac);
    }
	public Account findUserByEmail(String email) {
        return accountRepo.findByEmail(email);
    }
	
	public Optional<Account> findUser(String username) {
		return accountRepo.findById(username);
	}
	public Account getResetPasswordToken(String token) {
		return accountRepo.findByResetPasswordToken(token);
	}
	
	public void updateResetPasswordToken(String token, String email) throws Exception{
		Account account = accountRepo.findByEmail(email);
		if(account !=null) {
			account.setResetPasswordToken(token);
			accountRepo.save(account);
		}else {
			throw new Exception("Could not find any account with the email: "+email);
		}		
	}
	public void	updatePassword(Account account,String newPassword) {
		account.setPassword(newPassword);
		account.setResetPasswordToken(null);
		accountRepo.save(account);
	}
	


}
