package com.webstore.h4.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.webstore.h4.entity.Account;
import com.webstore.h4.service.UserService;
import com.webstore.h4.util.ForgotPasswordUtil;

import net.bytebuddy.utility.RandomString;
@Controller
public class forgotPasswordController {
	@Autowired(required = false)
	private JavaMailSender mailSender;
	@Autowired
	private UserService userService;
	
	@GetMapping("/forgot-password")
    public String showforgot() {   
        return "auth/forgot-password";
    }
	@PostMapping("/forgot-password")
	public String processPassword(HttpServletRequest request, Model model){
		String email = request.getParameter("email");
		String token = RandomString.make(30);
		try {
			userService.updateResetPasswordToken(token, email);
			String resetPasswordLink = ForgotPasswordUtil.getSiteURL(request)+ "/reset-password?token="+token;
			System.out.println(resetPasswordLink);
			sendEmail(email,resetPasswordLink);
			model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
		}catch(Exception e) {
			 e.printStackTrace();
			 model.addAttribute("error", "Error while sending email");
		}
		 return "auth/forgot-password";
	}
	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException{
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("toanvtps11241@fpt.edu.vn","IT Support");
		helper.setTo(recipientEmail);
		String subject = "Here's the link to reset your password";
		String content = "<p>Hello,</p>"
	            + "<p>You have requested to reset your password.</p>"
	            + "<p>Click the link below to change your password:</p>"
	            + "<p><a href=\"" + link + "\">Change my password</a></p>"
	            + "<br>"
	            + "<p>Ignore this email if you do remember your password, "
	            + "or you have not made the request.</p>";
		helper.setSubject(subject);
		helper.setText(content,true);
		mailSender.send(message);
	}
	
	@GetMapping("/reset-password")
    public String showResetPasswordForm(@Param(value="token") String token, Model model) {	
		Account account = userService.getResetPasswordToken(token);
		model.addAttribute("token", token);
		if(account == null) {
			model.addAttribute("message", "Invalid token");
			return "message";
		}
		return "auth/reset-password";
		
    }
     
    @PostMapping("/reset-password")
    public String processResetPassword(HttpServletRequest request , Model model) {
    	String token  = request.getParameter("token");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		Account account = userService.getResetPasswordToken(token);
		if(account == null) {
			model.addAttribute("message","Invalid Token");
			return "message";
		}if(!password.equals(confirm_password)){
			model.addAttribute("message","Password are not matching. Please try again !");
		}else {			
			userService.updatePassword(account, password);
			model.addAttribute("message","You have successfully changed your password.");
		}
		return "auth/reset-password";
    }
	
	
}
