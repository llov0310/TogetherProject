package com.together.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.together.domain.EnterpriseVO;
import com.together.service.CustomerService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HotelController {
	
	private CustomerService HotelService;
//
//	@RequestMapping(value = "/hotel_box", method=RequestMethod.POST)
//		@ResponseBody
//	   public String hotel_address(Model model, HttpSession session, @RequestParam String address) {
//		
//			ArrayList<EnterpriseVO> hotellist = new ArrayList<EnterpriseVO>();
//			String[] ad = address.split(" ");
//			String address_total = ad[2];
//			System.out.println(address_total);
//			hotellist = customerService.list(address_total);
//		
//			session.setAttribute("hotel_list", hotellist);
//  
//		   return "service/hotel";
//	   }
//	
	
	@RequestMapping(value = "/hotel_infomation", method=RequestMethod.GET)
		@ResponseBody
		public String hotel_info(Model model, HttpSession session, @RequestParam String test) {
			
			ArrayList<EnterpriseVO> hotel_info = new ArrayList<EnterpriseVO>();
			
			System.out.println(test);
			
			hotel_info = HotelService.info(test);

			model.addAttribute("m_info", hotel_info);
			session.setAttribute("info", hotel_info);
			
		   return "success";
	   }
	
	@RequestMapping(value = "/hotel_info", method=RequestMethod.GET)
		public String hotel_info_page(Model model, HttpSession session) {
			return "service/hotel/hotel_info_page";

	}
	
	   
	
}
