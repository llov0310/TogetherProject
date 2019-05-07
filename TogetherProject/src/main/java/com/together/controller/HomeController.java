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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.ProductVO;
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
   public String hotelserch(Model model ,HttpServletRequest request,HttpSession session ,@RequestParam String toAddress, EnterpriseVO ent) {
	   ArrayList<EnterpriseVO> result = new ArrayList<EnterpriseVO>();
	   
//	   int Auto = ((MemberVO) request.getSession().getAttribute("user")).getAuthority_no();
	   
	   model.addAttribute("place" , toAddress);
	   result = customerservice.ser(toAddress);
//	   session.setAttribute("etp_list" , result);
	   model.addAttribute("etp_list", result);
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
   public String etp_manage(Model model,HttpServletRequest request,HttpSession session) {

	  

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
   
   // 업체 이미지 변경
   @RequestMapping(value = "/etpimg", method=RequestMethod.GET)
   public String etp_img(Model model) {
	   
	   return "nav/enterprise_manage/enterprise_img";
   }
   
   //업체정보 업데이트 컨트롤러
   @RequestMapping(value = "/etpupdate", method=RequestMethod.POST)
   @ResponseBody
   public String etpUpdate(Model model,@RequestParam String etp_nm,
		   @RequestParam String etp_if_info,@RequestParam String etp_if_intro
		   ,@RequestParam String etp_addr,@RequestParam String etp_ph_no
		   ,@RequestParam String etp_license_no,@RequestParam String etp_email,
		   @RequestParam String time1,@RequestParam String time2,@RequestParam String etp_cd) {
	   
	   Integer update = customerservice.update(etp_nm,etp_addr,etp_ph_no,etp_license_no,etp_email,etp_cd);
	   Integer update2 = customerservice.update2(etp_if_info,etp_if_intro,time1,time2,etp_cd);
	   
	    
	   
	   return "tq";		
	   
   }
   
   //업체 상품 정보 추가/삭제/수정
   @RequestMapping(value = "/etpproduct", method=RequestMethod.GET)
   public String etp_product(Model model,HttpServletRequest request) {
	   
	   String id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
	   
	   ArrayList<EnterpriseVO> info = customerservice.info_select(id);
	   ArrayList<ProductVO> product = new ArrayList<ProductVO>();
	   
	   
	   String code = info.get(0).getEtp_cd();

	   product = customerservice.product_select(code);
	   
	   model.addAttribute("product_info", product);
	   
	   return "nav/enterprise_manage/enterprise_product";
   }
   
   // 업체 주문 현황
   @RequestMapping(value = "/etporder", method=RequestMethod.GET)
   public String etp_order(Model model) {
	   
	   return "nav/enterprise_manage/enterprise_order";
   }
   
   //업체 상품 추가 팝업창
   
   @RequestMapping(value = "/orderPopup", method=RequestMethod.GET)
   public String etp_order_pop(Model model) {
	   
	   return "nav/enterprise_manage/enterprise_order_pop";
   }
   
   //팝업창  상품  추가 버튼
   @RequestMapping(value = "/orderPopup_add", method=RequestMethod.GET)
   @ResponseBody
   public String etp_pop_add(HttpServletRequest request,Model model,@RequestParam String pd_nm,@RequestParam int pd_price,@RequestParam String pd_content) {
	  
	   String id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
	   
	   ArrayList<EnterpriseVO> info = customerservice.info_select(id);
	   
	   String code = info.get(0).getEtp_cd();
	   
	   System.out.println(code + "혹시 왓나");
	   
	   int product_insert = customerservice.insert_pro(code,pd_nm,pd_price,pd_content); //이미지 업로드가되면 추가로 넣을예정
	   
	   return "success";
   }
   
   
   
   
   // 업체 상품 제거 
   @RequestMapping(value = "/order_del", method=RequestMethod.POST)
   @ResponseBody
   public String etp_order_del(Model model,HttpServletRequest request,@RequestParam String nm) {
	   String id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
	   
	   ArrayList<EnterpriseVO> info = customerservice.info_select(id);
	   
	   String code = info.get(0).getEtp_cd();
	   	System.out.println(code + "코드번호");
	   int order_del = customerservice.del(code,nm);
	   
	   System.out.println(order_del + "왓습니까?");
	   return "success";
   }
   
   
  
   
}
