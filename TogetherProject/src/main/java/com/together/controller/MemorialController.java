package com.together.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.together.domain.SadBoardVO;
import com.together.service.CustomerService;
import com.together.service.MemorialService;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;




@Controller
@AllArgsConstructor
public class MemorialController {
	
		
	
       // memorialGallery에서 글쓰기 버튼 클릭시 폼 화면으로 이동 
	   @RequestMapping(value = "/writeForm", method=RequestMethod.GET)
	   public String writeFormPage(Model model) {
		   return "service/memorial/writeForm";
	   }
	   
	   
	   @RequestMapping(value = "/writeFormData", method=RequestMethod.GET)
	   	@ResponseBody
	   public String submitPost(
			   @RequestParam("sb_title") String sb_title, 
			   @RequestParam("sb_content") String sb_content, SadBoardVO sadBoardVom, Model model) {
		   
		   System.out.println("sb_title:::"+ sb_title +"sb_content:::"+sb_content);
		   
		   HashMap<String, Object> hashParam = new HashMap<String, Object>();
		   
		   
		   	
		   	
		   return "service/memorial";
	   }
	   
}