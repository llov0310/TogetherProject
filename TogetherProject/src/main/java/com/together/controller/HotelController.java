package com.together.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.service.CustomerService;
import net.sf.json.*;


import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HotelController {
	
	private CustomerService HotelService;

	@RequestMapping(value = "/hotel_infomation", method=RequestMethod.GET)
		@ResponseBody
		public String hotel_info(Model model, HttpSession session, @RequestParam String test) {
			
			ArrayList<EnterpriseVO> hotel_info = HotelService.info(test);
			
			ArrayList<EnterpriseVO> hotel_product = new ArrayList<EnterpriseVO>();
			
			String code = hotel_info.get(0).getEtp_cd();
			
			hotel_product = HotelService.getList(code);
			
			
//			model.addAttribute("m_info", hotel_info);
			session.setAttribute("product", hotel_product);
			session.setAttribute("info", hotel_info);
			
		   return "success";
	   }
	
	@RequestMapping(value = "/hotel_info", method=RequestMethod.GET)
		public String hotel_info_page(Model model, HttpSession session) {
			return "service/hotel/hotel_info_page";

	}
	
	//업체 주문
	@RequestMapping(value = "/buy_book", method=RequestMethod.POST)
	@ResponseBody
	public String hotel_book_buy(Model model, HttpSession session, HttpServletRequest request, @RequestBody String param) {
		  int price = 0;
		  String user = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
//		  String code = ((EnterpriseVO) request.getSession().getAttribute("info")).getEtp_cd();
		  ArrayList<EnterpriseVO> product = new ArrayList<EnterpriseVO>();
		  List<Map<String,Object>> paymentMap = new ArrayList<Map<String,Object>>();
		  paymentMap = JSONArray.fromObject(param);
		  for(int i=0; i<paymentMap.size(); i++) {
		  String code = (String) paymentMap.get(i).get("etp_cd");
		  String name = (String) paymentMap.get(i).get("pd_nm");
		  String first_day = (String) paymentMap.get(i).get("first");
		  String last_day = (String) paymentMap.get(i).get("last");
		  String total_day = (String) paymentMap.get(i).get("num");
		  String price1 = (String) paymentMap.get(i).get("price");
		  price = Integer.parseInt(total_day) * Integer.parseInt(price1);
		  
//		  System.out.println(Integer.parseInt(total_day));
		  product = HotelService.info_list(code,name);
		  
		  String pdcode = product.get(0).getPd_cd();
		  
		  
		  int order_buy = HotelService.insert_order(
				  user,pdcode,first_day,last_day,price);
		  
		  System.out.println(first_day + " " + last_day + " " + pdcode);
		  System.out.println(first_day.length());
		  
		  
		  String total_first = "";
		  String total_last = "";
		  
		  if(first_day.length() != 9 || last_day.length() != 9) {
			 
			 if(first_day.length() != 9) {
			 String first_day_convert = first_day.substring(5);
			 String[] aa = first_day_convert.split("-");
			 total_first = aa[0] + "-" + "0" + aa[1];
			 System.out.println(total_first);
			 
			 if(last_day.length() != 9) {
				 String last_day_convert = last_day.substring(5);
			     String[] bb = last_day_convert.split("-");
				 total_last = bb[0] + "-" + "0" + bb[1];
				 System.out.println(total_last);	 
			 }
			 
			 if(total_first.length() == 4 && total_last.length() == 4) {
				 int order_buy_update = HotelService.update_order(total_first,total_last,pdcode);
			 }else if(total_first.length() == 4){
				 int order_buy_update = HotelService.update_order(total_first,last_day,pdcode);
			 }
			 
			 return "success";
			 
		  }else {
			 int order_buy_update = HotelService.update_order(first_day,last_day,pdcode);
			 return "success";
		  }
//		  
		  
	  }
	 
	}  
		  return "success";

}
	
	//리뷰게시판으로 이동
	@RequestMapping(value = "/hotelReview", method=RequestMethod.GET)
	public String hotelReview(Model model, HttpSession session) {
		
		return "service/hotel/hotelReview";

	}
	
	//리뷰 게시판 작성 insert 구문
	
}
