package com.example.hellomx;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.hellomx.domain.MemberVO;
import com.example.hellomx.service.CustomerService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {
	
	private CustomerService customerService;
	
	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO userInfo, HttpSession session, Model model) {
		
		MemberVO user = customerService.login(userInfo.getUserid(), userInfo.getPassword());
		
		
		if (user.getAuthority().equals("1")) {
			session.setAttribute("user", user);
			return "admin/adminHome";
		} else if(user.getAuthority().equals("2")){
			session.setAttribute("user", user);
			return "";
		} else if(user.getAuthority().equals("3")){
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
