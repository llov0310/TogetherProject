package com.together.hellomx;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.together.domain.MemberVO;
import com.together.service.CustomerService;

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
