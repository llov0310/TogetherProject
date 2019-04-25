package com.together.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.together.domain.SadBoardVO;
import com.together.service.MemorialService;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class MemorialController {
	
		
	
       // memorialGallery에서 글쓰기 버튼 클릭시 폼 화면으로 이동 
	   @RequestMapping(value = "/writeForm", method=RequestMethod.GET)
	   public String writeFormPage(Model model) {
		   return "service/memorial/writeForm";
	   }
	   
	   
	   MemorialService memorialService;
	   
	   //글 목록 출력
	   @RequestMapping(value = "list.do", method=RequestMethod.GET)
	   public ModelAndView list() throws Exception {
		   
		   List<SadBoardVO> list = memorialService.listAll(0, 0); // 목록 
		   
		   ModelAndView mav = new ModelAndView();
		   mav.setViewName("service/memorial"); // 이동할 페이지 지정 
		   Map<String, Object> map = new HashMap<>();
		   map.put("list", list); // 맵에 자료 저장 
		   ((ModelAndView) map).addObject("map", map); // 데이터 저장 
		   return mav; // 페이지 이동(출력) 
		   
	   }
	   
	
	   // 글쓰기 Form에서 데이터 받기
	   @RequestMapping(value = "/writeFormData", method=RequestMethod.GET)
	   public String insert(
			   @ModelAttribute SadBoardVO sadVO, 
			   HttpSession session) throws Exception {
		   
		   
		   //로그인 사용자 아이디 받자  
		   String user_id = (String) session.getAttribute("user_id");
		   sadVO.setUser_id(user_id);
		   
		   System.out.println("sb_title::: "+ sadVO.getSb_title() +"sb_content:::"+sadVO.getSb_content()  
		   +"user_id::::"+sadVO.getUser_id());
		   
		   return "service/memorial";
	   }
	   
}