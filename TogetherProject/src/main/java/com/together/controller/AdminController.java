package com.together.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.together.domain.DogsVO;
import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.PageMaker;
import com.together.domain.Paging;
import com.together.mapper.AdminMapper;
import com.together.service.AdminService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AdminController {
	private AdminService adminService;
	private AdminMapper adminMapper;
	   //관리자 홈 페이지 맵핑
	   @RequestMapping(value = "/adminHome", method=RequestMethod.GET)
	   public String adminHome(Model model) {
		   return "admin/adminHome";
	   }
   
	   // 회원관리 페이징 맵핑 (페이징 적용)
	   @RequestMapping(value = "/memberManage" + "/{num}", method = RequestMethod.GET)
	   public String memberManage(@PathVariable String num, Model model, HttpSession session) {

		   Paging page = new Paging();
		   int pageNum = 0;
		   ArrayList<Integer> arr = new ArrayList<Integer>();
		   int realNum = Integer.parseInt(num);
		   page.setTotalNum(adminService.getPageNum());
		   
		   //OnePageBorad => 한 페이지에 보여줄 멤버(글) 수
		   if(page.getTotalNum() <= page.getOnePageBoard()) { // totalnum이 10보다 작으면 pageNum을 1로 설정
			   pageNum = 1;
		   }else { // totalnum이 더 클 경우
			   pageNum = page.getTotalNum() / page.getOnePageBoard(); //totalnum / 10
			   															// ex) 21/ 10 = 2가 pageNum에 들어감
			   if(page.getTotalNum() %page.getOnePageBoard() > 0 ) {	// 그리고 21 % 10 나머지 1이 0보다 크기때문에 pageNum에 2+1=3이 들어감
				   pageNum = pageNum + 1;
			   }
		   }
		   
		   // for문으로 pageNum만큼 arr에 
		   // arr[0] = 1, arr[1] = 2, arr[2] = 3 을 저장
		   for(int i=0; i<pageNum; i++) {
			   arr.add(i+1);
		   }
		   
		   page.setEndNum((realNum*10) +1);
		   page.setStartNum(page.getEndNum()-10);
		   
		   //
		   model.addAttribute("pageNum", arr);
		   model.addAttribute("memberList", adminService.memberList(page));
		   return "admin/memberManage";
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
	   @ResponseBody
	   public String etpApplyManage(
			   Model model, HttpSession session,
			   EnterpriseVO etpIns, MemberVO mbIns,
			   @RequestParam String[] user_id, @RequestParam String etpCk
			   ) {
		   
		   //System.out.println(user_id.length);
		   //System.out.println(etpCk + "옴??????????????");
		   if(etpCk.equals("수락")) {
			   for(int i=0; i<user_id.length; i++) {
				   int update = adminService.etpApplyManage_01(user_id[i]);
				   
				   if(update !=0) {
					   return "admin/enterpriseManage";
				   } else {
					   return "admin/enterpriseManage";
				   }
			   
			   }
		   }else if(etpCk.equals("거절")) {
			   for(int i=0; i<user_id.length; i++) {
				   int delete = adminService.etpApplyManage_02(user_id[i]);
				   
				   if(delete !=0) {
					   return "admin/enterpriseManage";
				   } else {
					   return "admin/enterpriseManage";
				   }
			   }
		   }
		   
		   
		   //System.out.println("넘어는 오시는건가요?");
		   
		   
		  
		   return "admin/enterpriseManage";
	   }
	   
	   
	   
	   //반려견 관리 (페이징 적용)
	   @RequestMapping(value= "/dogsManage" + "/{num}", method=RequestMethod.GET)
	   public String dogsManage(@PathVariable String num, Model model, HttpSession session) {
		   
		   Paging page = new Paging();
		   int pageNum = 0;
		   ArrayList<Integer> arr = new ArrayList<Integer>();
		   int realNum = Integer.parseInt(num);
		   page.setTotalNum(adminService.getPageNum());
		   
		   if(page.getTotalNum() <= page.getOnePageBoard()) {
			   pageNum = 1;
		   }else {
			   pageNum = page.getTotalNum() / page.getOnePageBoard();
			   if(page.getTotalNum() % page.getOnePageBoard() > 0) {
				   pageNum = pageNum + 1;
			   }
		   }
		   
		   //for 문으로 pageNum만큼 arr에
		   //arr[i] 저장
		   for(int i=0; i<pageNum; i++) {
			   arr.add(i+1);
		   }
		   
		   page.setEndNum((realNum*10) + 1);
		   page.setStartNum(page.getEndNum() - 10);
		   
		   model.addAttribute("pageNum", arr);
		   //sql 쿼리문
		   model.addAttribute("dogsList", adminService.dogsList(page));
		   
		   return "admin/dogsManage";
	   }

}