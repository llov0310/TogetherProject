package com.together.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.together.domain.MemberVO;
import com.together.service.AdminService;
import com.together.service.CustomerService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {
	
	private CustomerService customerService;
	private AdminService adminService; // 관리자 홈페이지를 위해 선언
	
	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO userInfo, HttpSession session, Model model) {	
		MemberVO user = customerService.login(userInfo.getUser_id(), userInfo.getPassword());
		
//		System.out.println(user.getAuthority_no());
		if (user.getAuthority_no() == 1) {
			session.setAttribute("user", user);
			
			int memberCnt = adminService.memberCnt(); //총 회원수를 담기 위한 변수 선언
			int etpApplyCnt = adminService.etpApplyCnt(); //총 업체 신청를 담기 위한 변수 선언
			int dogsCnt = adminService.dogsCnt();// 반려견 수를 담기 위한 변수 선언
			int etpCnt = adminService.etpCnt(); // 등록 된 업체 수를 담기 위한 변수 선언
			
			System.out.println(memberCnt + "멤버수 : 로그인 컨트롤러");
			System.out.println(etpApplyCnt + "업체 신청 수 : 로그인 컨트롤러");
			System.out.println(dogsCnt + "반려견 수 : 로그인 컨트롤러");
			System.out.println(etpCnt + "반려견 수 : 로그인 컨트롤러");
			
			model.addAttribute("etpApplyCnt", etpApplyCnt);
			model.addAttribute("memberCnt", memberCnt);
			model.addAttribute("dogsCnt", dogsCnt);
			model.addAttribute("etpCnt", etpCnt);
			
			return "admin/adminHome";
			
		} else if(user.getAuthority_no() == 2){
			session.setAttribute("user", user);
			return "home";
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
