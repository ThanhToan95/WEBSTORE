package com.webstore.h4.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.webstore.h4.dto.UserDto;
import com.webstore.h4.entity.Account;
import com.webstore.h4.service.UserService;

@Controller
public class RegisterController {
	@Autowired
	UserService userService;
	
	@GetMapping("/register")
    public String registerForm(Model model) {   
		UserDto user = new UserDto();
		model.addAttribute("user",user);
        return "auth/register";
    }
	@PostMapping("/auth/register")
	public String submitForm(@ModelAttribute("user") @Valid UserDto userDto,  BindingResult result,
            											Model model) throws Exception {
		Account existingEmail = userService.findUserByEmail(userDto.getEmail());
		Optional<Account> existingUser = userService.findUser(userDto.getUsername());
			if(existingUser.isPresent()) {
				result.rejectValue("username", null,
	                    "There is already an account registered with the same username");
			}
	        if(existingEmail != null && existingEmail.getEmail() != null && !existingEmail.getEmail().isEmpty()){
	            result.rejectValue("email", null,
	                    "There is already an account registered with the same email");
	        }

	        if(result.hasErrors()){
	            model.addAttribute("user", userDto);
	            return "auth/register";
	        }

	        userService.saveUser(userDto);
	        model.addAttribute("successs","You have successfully registered our app!");
	        return "auth/register";
	    }

	}

