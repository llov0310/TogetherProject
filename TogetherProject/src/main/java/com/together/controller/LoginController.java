package com.together.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.together.domain.MemberVO;
import com.together.service.CustomerService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {
	
	private CustomerService customerService;
	
	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO userInfo, HttpSession session, Model model) {
		
		MemberVO user = customerService.login(userInfo.getUser_id(), userInfo.getPassword());
		
		
		if (user.getAuthority_no() == 1) {
			session.setAttribute("user", user);
			return "admin/adminHome";
		} else if(user.getAuthority_no() == 2){
			session.setAttribute("user", user);
			return "";
		} else if(user.getAuthority_no() == 3){
			session.setAttribute("user", user);
			return "home";
		} else {
			model.addAttribute("error", "login failed");
			return "login";
		}
	}
	
	//로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String loginForm(HttpSession session) {
		session.invalidate();
		return "home";
	}
}
