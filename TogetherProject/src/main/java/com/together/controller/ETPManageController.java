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
import com.together.domain.ProductVO;
import com.together.service.CustomerService;
import com.together.service.ETPManageService;

import net.sf.json.*;


import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ETPManageController {
	
	private ETPManageService ManageService;
	
	
	
		////////////////////////////////////업체 관리 용////////////////////////////////////  
		//업체 관리 페이지 맵핑
		@RequestMapping(value = "/etpManage", method=RequestMethod.GET)
		public String etp_manage(Model model,HttpServletRequest request,HttpSession session) {
		
		String sess = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		
		ArrayList<EnterpriseVO> ent_info = new ArrayList<EnterpriseVO>();
		
		ent_info = ManageService.etpcheck(sess);
		
		model.addAttribute("etpInfo", ent_info);
		
		return "nav/enterprise_manage/enterprise_manage";
		}
		
		
		@RequestMapping(value = "/etphome", method=RequestMethod.GET)
		public String etp_home(Model model, HttpSession session, HttpServletRequest request) {
		
		
		String sess = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		
		ArrayList<EnterpriseVO> ent = new ArrayList<EnterpriseVO>();
		
		ent = ManageService.textbox(sess);
		
		model.addAttribute("list", ent);
		
		
		
		
		return "nav/enterprise_manage/enterprise_home";
		}
		
	 //업체정보 업데이트 컨트롤러
	   @RequestMapping(value = "/etpupdate", method=RequestMethod.POST)
	   @ResponseBody
	   public String etpUpdate(Model model,@RequestParam String etp_nm,
			   @RequestParam String etp_if_info,@RequestParam String etp_if_intro
			   ,@RequestParam String etp_addr,@RequestParam String etp_ph_no
			   ,@RequestParam String etp_license_no,@RequestParam String etp_email,
			   @RequestParam String time1,@RequestParam String time2,@RequestParam String etp_cd) {
		   
		   Integer update = ManageService.update(etp_nm,etp_addr,etp_ph_no,etp_license_no,etp_email,etp_cd);
		   Integer update2 = ManageService.update2(etp_if_info,etp_if_intro,time1,time2,etp_cd);
		   
		    
		   
		   return "tq";		
		   
	   }
	   
	   
	   // 업체 이미지 변경
	   @RequestMapping(value = "/etpimg", method=RequestMethod.GET)
	   public String etp_img(Model model) {
		   
		   return "nav/enterprise_manage/enterprise_img";
	   }
	   
	  
	   
	   //업체 상품 정보 추가/삭제/수정
	   @RequestMapping(value = "/etpproduct", method=RequestMethod.GET)
	   public String etp_product(Model model,HttpServletRequest request) {
		   
		   String id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		   
		   ArrayList<EnterpriseVO> info = ManageService.info_select(id);
		   ArrayList<ProductVO> product = new ArrayList<ProductVO>();
		   
		   
		   String code = info.get(0).getEtp_cd();

		   product = ManageService.product_select(code);
		   
		   
		   
		   model.addAttribute("product_info", product);
		   
		   
		   return "nav/enterprise_manage/enterprise_product";
	   }
	   
	   // 업체 주문 현황
	   @RequestMapping(value = "/etporder", method=RequestMethod.GET)
	   public String etp_order(Model model, HttpServletRequest request,HttpSession session) {
		   
		   String sess = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		   ArrayList<EnterpriseVO> orderlist = new ArrayList<EnterpriseVO>();
		   
		   ArrayList<EnterpriseVO> ent_list = new ArrayList<EnterpriseVO>();
			
		   ent_list = ManageService.textbox(sess);
		   
		   String code = ent_list.get(0).getEtp_cd();
		   

		   orderlist = ManageService.select_order_list(code);
		   
		   model.addAttribute("orderLists", orderlist);
//		   session.setAttribute("orderLists", orderlist);

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
		   
		   ArrayList<EnterpriseVO> info = ManageService.info_select(id);
		   
		   String code = info.get(0).getEtp_cd();
		   
		   System.out.println(code + "혹시 왓나");
		   
		   int product_insert = ManageService.insert_pro(code,pd_nm,pd_price,pd_content); //이미지 업로드가되면 추가로 넣을예정
		   
		   return "success";
	   }
	   
	   
	   
	   
	   // 업체 상품 제거 
	   @RequestMapping(value = "/order_del", method=RequestMethod.POST)
	   @ResponseBody
	   public String etp_order_del(Model model,HttpServletRequest request,@RequestParam String nm) {
		   String id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		   
		   ArrayList<EnterpriseVO> info = ManageService.info_select(id);
		   
		   String code = info.get(0).getEtp_cd();
		   	System.out.println(code + "코드번호");
		   int order_del = ManageService.del(code,nm);
		   
		   System.out.println(order_del + "왓습니까?");
		   return "success";
	   }
	
}