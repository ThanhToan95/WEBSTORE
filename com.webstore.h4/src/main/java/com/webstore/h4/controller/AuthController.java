package com.webstore.h4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
	@RequestMapping("/auth/login/form")
	public String loginForm() {		
		return "auth/login";
	}
	@RequestMapping("/auth/login/success")
	public String success(Model model) {
		model.addAttribute("message", "Đăng nhập thành công !!!");
		return "auth/login";
	}
	@RequestMapping("/auth/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Đăng nhập thất bại !!!");
		return "auth/login";
	}
	
}	
