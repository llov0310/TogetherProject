package com.together.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HotelController {

	@RequestMapping(value = "/hotel_box", method=RequestMethod.POST)
		@ResponseBody
	   public String hotel_address(Model model, HttpSession session, @RequestParam String address) {
		
		
		   
		   return "service/hotel";
	   }
	   
	
}
