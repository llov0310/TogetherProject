package com.together.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.together.domain.DogsVO;
import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
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

	   //회원 관리 페이지 맵핑 및 회원 목록 보여줌
	   @RequestMapping(value = "/customerManage", method=RequestMethod.GET)
	   public String customerManage(Model model, HttpSession session) {
		   
		   ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		   memberList = adminService.getMemberList();
		   //System.out.println(memberList + "여기까지옴");
		   session.setAttribute("memberList", memberList);
		   
		   return "admin/customerManage";
	   }
	   
	   //업체 관리 페이지 맵핑 및 업체 업체 목록 보여줌
	   @RequestMapping(value = "/enterpriseManage", method=RequestMethod.GET)
	   public String enterpriseManage(Model model, HttpSession session) {
		   
		   ArrayList<EnterpriseVO> enterpriseList = new ArrayList<EnterpriseVO>();
		   enterpriseList = adminService.getEnterpriseList();
		   //System.out.println(enterpriseList + "db에서 가져왔다@@@");
		   
		   session.setAttribute("enterpriseList", enterpriseList);
		   
		   return "admin/enterpriseManage";
	   }
	   
	   //업체 신청 수락 및 거절 맵핑
	   @RequestMapping(value = "/etpApplyManage", method=RequestMethod.POST)
	   public String etpApplyManage(Model model,
			   EnterpriseVO etpIns, MemberVO mbIns,
			   @RequestParam String etpApplyCk,
			   @RequestParam String user_id,
			   HttpSession session){
	   
//		   System.out.println(etpIns + "아이디 찍히는건가?");
//		   System.out.println("AdminController : " + etpApplyCk);
//		   System.out.println(user_id);
		   
		   String[] arr = user_id.split(",");
		   
		   System.out.println(arr[0] + ": 1번짼데여");
		   System.out.println(arr[1] + ": 2번짼데여");
		   
		   
		   if(etpApplyCk.equals("수락")) {
			   
			   for(int i=0; i<arr.length; i++) { 
				   int update = adminService.etpApplyManage_01(mbIns, arr[i]);
				   
				   System.out.println(update+"ㅎㅎㅎ여긴데");
				   
//				   if (update != 0) {
//					   
//					   return "admin/enterpriseManage";
//				   } else {
//					   return "admin/adminHome";
//				   }
			   }
			  
		   }
		   
//		   }else if(etpApplyCk.equals("거절")) {
//			   
//		   }
		   
		   return "admin/enterpriseManage";
	   }
	   
	   //반려견 관리 
	   @RequestMapping(value= "/dogsManage", method=RequestMethod.GET)
	   public String dogsManage(Model model, HttpSession session) {

		   ArrayList<DogsVO> dogsList = new ArrayList<DogsVO>();
		   dogsList = adminService.getDogsList();
		   //System.out.println(dogsList + "db에서 가져왔다@@@");
		   session.setAttribute("dogsList", dogsList);
		   
		   return "admin/dogsManage";
	   }

}