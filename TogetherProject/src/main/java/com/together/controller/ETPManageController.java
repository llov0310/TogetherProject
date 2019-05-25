package com.together.controller;

import java.lang.reflect.Array;
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
import com.together.domain.OrdersVO;
import com.together.domain.ProductVO;
import com.together.domain.StockVO;
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
	   public String etp_pop_add(HttpServletRequest request,Model model,@RequestParam String pd_nm,@RequestParam int pd_price,@RequestParam String pd_content,@RequestParam String pd_num ) {
		  
		   String id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		   
		   ArrayList<EnterpriseVO> info = ManageService.info_select(id);
		   
		   String code = info.get(0).getEtp_cd();
		   
		   int product_insert = ManageService.insert_pro(code,pd_nm,pd_price,pd_content); //이미지 업로드가되면 추가로 넣을예정
		   
		   ArrayList<ProductVO> select_product = ManageService.st_insert_pro(code,pd_nm);
		   
		   String pro_code = select_product.get(0).getPd_cd();
		   
		   
		   for(int i=5; i<13; i++) {
			   
			   for(int f=1; f<=30; f++) {
				   
				   if(i < 10 && f<10) {
					   String total_code = pro_code + "-"+ "0" + i + "-" + "0" + f;
					   int stock_insert = ManageService.stockint(total_code,pro_code,pd_num);
				   }else if(f<10){
						String total_code = pro_code + "-" + i + "-" +"0"+ f;
						int stock_insert = ManageService.stockint(total_code,pro_code,pd_num);	   
				   }else if(i<10) {
					   String total_code = pro_code + "-" +"0"+ i + "-" + f;
						int stock_insert = ManageService.stockint(total_code,pro_code,pd_num);
				   }else {
					   String total_code = pro_code + "-" + i + "-" + f;
						int stock_insert = ManageService.stockint(total_code,pro_code,pd_num);
				   }
				   
				  if(f==30){ 
					  if(i==5 || i==7 || i==8) {
					   String total_code = pro_code + "-" + "0" + i + "-" + "31";
					   int stock_insert = ManageService.stockint(total_code,pro_code,pd_num);
					  }else if(i==10 || i == 12) {
						  String total_code = pro_code + "-"+ i + "-" + "31";
						  int stock_insert = ManageService.stockint(total_code,pro_code,pd_num);
					  }

				  }
				   	
			   }
			   if(i == 12) {
				   return "success";
			   }
		   }
		   
		   return "success";
	   }
	   
	   
	   
	   
	   // 업체 상품 제거 
	   @RequestMapping(value = "/order_del", method=RequestMethod.POST)
	   @ResponseBody
	   public String etp_order_del(Model model,HttpServletRequest request,@RequestParam String nm) {
		   String id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		   
		   ArrayList<EnterpriseVO> info = ManageService.info_select(id);
		   
		   String code = info.get(0).getEtp_cd();
		   	
		   int order_del = ManageService.del(code,nm);
		   System.out.println(order_del);
		   System.out.println("넘어옵니까?");
		   return "success";
	   }
	   
	   
	   // 업체 확인 업데이트
	   @RequestMapping(value = "/orderChecklevel", method=RequestMethod.POST)
	   @ResponseBody
	   public String orderCheck(Model model,
			   @RequestParam String day1,
			   @RequestParam String day2,
			   @RequestParam String nm,
			   @RequestParam String check_val,
			   @RequestParam String day_th
			   ) {
		   
		   ArrayList<OrdersVO> updateCheck = ManageService.newinfo(nm);
		   
		   String member_id = updateCheck.get(0).getUser_id();

		   int up = ManageService.updateChecked(day1,day2,check_val,member_id,day_th);
		   
		
		   return "success";
	   }
	   
	   
	   @RequestMapping(value = "/join", method=RequestMethod.POST)
	   @ResponseBody
	   public ArrayList<String> Stock_check(Model model,
			   @RequestParam  String sub_fir,
			   @RequestParam  String sub_las,
			   @RequestParam  String content,
			   @RequestParam  String p_nm,
			   @RequestParam  String code) {
		   
		   ArrayList<ProductVO> check_pro = ManageService.checkPro(code,content,p_nm); //해당 상품코드조회
		   
		   String p_code = check_pro.get(0).getPd_cd();

		   ArrayList<StockVO> check_Stock = ManageService.StockCheckPro(p_code,sub_fir,sub_las); //재고 조회
		   int i;
		   
//		   for(i=0; i<check_Stock.size(); i++) {
//			  	if(check_Stock.get(i).getSt_this_num() == 0) {			  		
//			  		return check_Stock.get(i).getSt_cd();
//			  	}
//		  }
//		  return "success";
		  
		  //다음부터 해야할 부분입니다
		  ArrayList<String> re_list = new ArrayList<String>();
		  
		  if(check_Stock.size() != 0) {
			  
			   for(i=0; i<check_Stock.size(); i++) {
				  	if(check_Stock.get(i).getSt_this_num() == 0) {
				  		String list = check_Stock.get(i).getSt_cd();
				  		re_list.add(list);
				  	}
			   }
			   System.out.println(re_list);
			   return re_list;
		  }else {
			  re_list.add("fail");
			  return re_list;
		  }
		  
	   }
}