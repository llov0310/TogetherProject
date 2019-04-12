package com.together.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;



/**
 * Handles requests for the application home page.
 */
@Controller
@AllArgsConstructor
public class HomeController {
	
   //홈 페이지 맵핑
   @RequestMapping(value = "/", method=RequestMethod.GET)
   public String home(Model model) {
	   return "home";
   }
   
   //서비스 페이지 맵핑
   @RequestMapping(value = "/services", method=RequestMethod.GET)
   public String services(Model model) {
	   return "nav/services";
   }
   
   //로그인 페이지 맵핑
   @RequestMapping(value = "/login", method=RequestMethod.GET)
   public String login(Model model) {
	   
	   return "nav/login";
   }
   
   //회원가입 페이지 맵핑
   @RequestMapping(value = "/signup", method=RequestMethod.GET)
   public String signup(Model model) {
	   
	   return "nav/signup";
   }
   
   //호텔, 병원, 장례, 추모게시판 같이 있는 페이지 맵핑
   @RequestMapping(value = "/page", method=RequestMethod.GET)
   public String menuPage(Model model) {
	   
	   return "service/page";
   }
   
   //호텔 페이지 맵핑
   @RequestMapping(value = "/hotel", method=RequestMethod.GET)
   public String hotel(Model model) {
	   
	   return "service/hotel";
   }
   
   //호텔 페이지 관련 이동 맵핑 정보
   @RequestMapping(value = "/hotelserch", method=RequestMethod.GET)
   public String hotelserch(Model model , @RequestParam String toAddress) {
	  
	   model.addAttribute("place" , toAddress);
	  
	  return  "service/hotel/hotelserch";
   }
   
   //병원 페이지 맵핑
   @RequestMapping(value = "/hospital", method=RequestMethod.GET)
   public String hospital(Model model) {
	   
	   return "service/hospital/hospital_list";
   }
   
   //펫 장례 페이지 맵핑
   @RequestMapping(value = "/goodbye1", method=RequestMethod.GET)
   public String goodbye1(Model model) {
	   
	   return "service/goodbye1/goodbye1_home";
   }
   
   //추모 게시판 페이지 맵핑
   @RequestMapping(value = "/memorialGallery", method=RequestMethod.GET)
   public String memorialGallery(Model model) {
	   
	   return "service/memorial/memorialGallery";
   }
   
   //마이 페이지 맵핑
   @RequestMapping(value = "/mypage", method=RequestMethod.GET)
   public String mypage(Model model) {
	   
	   return "nav/mypage";
   }
   
   //업체 신청 페이지 맵핑
   @RequestMapping(value = "/etpApply", method=RequestMethod.GET)
   public String etpApply(Model model) {
	   
	   return "nav/etpApply";
   }
  
   
   
}
