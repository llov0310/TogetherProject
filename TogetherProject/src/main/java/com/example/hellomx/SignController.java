package com.example.hellomx;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.hellomx.domain.MemberVO;
import com.example.hellomx.service.CustomerService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SignController {
	
	private CustomerService customerService;

	// 회원가입
	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public String signup(Model model, MemberVO ins) {

		int insert = customerService.signup(ins);

		if (insert != 0) {
			return "nav/login";
		} else {
			return "home";
		}

	}
}
