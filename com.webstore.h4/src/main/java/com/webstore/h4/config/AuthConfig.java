package com.webstore.h4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.webstore.h4.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	UserService userService;
	
	@Bean 
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		 auth.//inMemoryAuthentication().withUser("ram").password(pe.encode("123")).roles("Admin");
		userDetailsService(userService).passwordEncoder(getPasswordEncoder());
	}
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable();
        http.authorizeRequests()
                .antMatchers("/auth/login/**").permitAll();
                //.anyRequest().authenticated();
				/*
				 * .antMatchers("/program/").access("hasRole(ROLE_USER')")
				 * .antMatchers("/admin").access("hasRole(ROLE_ADMIN')")
				 */
        http.formLogin()
        	.loginPage("/auth/login/form")
        	.loginProcessingUrl("/auth/login")
        	.defaultSuccessUrl("/auth/login/success",false)
        	.failureUrl("/auth/login/error")
        	.usernameParameter("username")
        	.passwordParameter("password");
        http.rememberMe().rememberMeParameter("remember");
    }
}
