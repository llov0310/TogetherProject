package com.together.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.together.service.AdminService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AdminController {
	private AdminService adminService;
	
	   //관리자 홈 페이지 맵핑
	   @RequestMapping(value = "/adminHome", method=RequestMethod.GET)
	   public String adminHome(Model model) {
		   return "admin/adminHome";
	   }
	   
	   //관리자 : 회원 관리 페이지
	   @RequestMapping(value = "/customerManage", method=RequestMethod.GET)
	   public String customerManage(Model model) {
		   
		   return "admin/customerManage";
	   }
	   

}
