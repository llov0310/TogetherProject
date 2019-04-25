package com.together.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.service.CustomerService;

import lombok.AllArgsConstructor;



/**
 * Handles requests for the application home page.
 */
@Controller
@AllArgsConstructor
public class HomeController {
	
	private CustomerService customerservice;
	
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
   public String hotelserch(Model model ,HttpSession session ,@RequestParam String toAddress, EnterpriseVO ent) {
	   ArrayList<EnterpriseVO> result = new ArrayList<EnterpriseVO>();

	   model.addAttribute("place" , toAddress);
	   result = customerservice.ser(toAddress);

	   session.setAttribute("list_enterprise", result);
	  
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
  
   
 ////////////////////////////////////업체 관리 용////////////////////////////////////  
   //업체 관리 페이지 맵핑
   @RequestMapping(value = "/etpManage", method=RequestMethod.GET)
   public String etp_manage(Model model) {
	   
	   
	   
	   return "nav/enterprise_manage/enterprise_manage";
   }
   
   
   @RequestMapping(value = "/etphome", method=RequestMethod.GET)
   public String etp_home(Model model, HttpSession session, HttpServletRequest request) {
	   
	   
	  String sess = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
	  	
	   
	   ArrayList<EnterpriseVO> ent = new ArrayList<EnterpriseVO>();
	   
	   ent = customerservice.textbox(sess);
	   
	   model.addAttribute("list", ent);
	   
	   
	   
	   return "nav/enterprise_manage/enterprise_home";
   }
   
   
   @RequestMapping(value = "/etpimg", method=RequestMethod.GET)
   public String etp_img(Model model) {
	   
	   return "nav/enterprise_manage/enterprise_img";
   }
   
}
