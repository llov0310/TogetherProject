package com.together.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.together.domain.DogsVO;


import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MyPageController {
	
	
	
	
	@RequestMapping(value = "/member_info", method=RequestMethod.GET)
	   public String member_info(Model model) {
		   return "nav/mypage/member_info";   
		   
	   }
	
	@RequestMapping(value = "/mypet_info", method=RequestMethod.GET)
	 public String mypet_info(Model model) {
		   return "nav/mypage/mypet_info";   
	   }
	
	@RequestMapping(value = "/myreservation", method=RequestMethod.GET)
	   public String myreservation(Model model) {
		   return "nav/mypage/myreservation";
		   
		   
	   }
	
	@RequestMapping(value = "/mypost", method=RequestMethod.GET)
	   public String mypost(Model model) {
		   return "nav/mypage/mypost";
		   
		   
	   }
	@RequestMapping(value = "/deleteaccount", method=RequestMethod.GET)
	   public String DeleteAccount(Model model) {
		   return "nav/mypage/DeleteAccount";
		   
		   
	   }
	
}
