package com.together.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.together.domain.MemberVO;
import com.together.service.MypageService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MyPageController {
	
	
	private MypageService mypage;
	
	@RequestMapping(value = "/member_info", method=RequestMethod.GET)
	   public String member_info(Model model) {
		   return "nav/mypage/member_info";   
		   
	   }
	
	//패스워드 값을 비교하는 구문
	@RequestMapping(value = "/member_pass_cheak", method=RequestMethod.POST)
		@ResponseBody
	   public String member_pass_cheak(Model model, @RequestParam String password, @RequestParam String user_id) {
		  
			ArrayList<MemberVO> pass_cheak = new ArrayList<MemberVO>();
			System.out.println(password);
			pass_cheak = mypage.passCheak(user_id);
			
			String a = pass_cheak.get(0).getPassword();
		
			if(a.equals(password)) {
				return "success";
			}else {
				return "fail";
			
			}

	}
	
	@RequestMapping(value = "/mypet_info", method=RequestMethod.GET)
	 public String mypet_info(Model model) {
		   return "nav/mypage/mypet_info";   
	   }
	
	@RequestMapping(value = "/myreservation", method=RequestMethod.GET)
	   public String myreservation(Model model) {
		   return "nav/mypage/myreservation";
		   
		   
	   }
	
	@RequestMapping(value = "/mypost", method=RequestMethod.GET)
	   public String mypost(Model model) {
		   return "nav/mypage/mypost";
		   
		   
	   }
	@RequestMapping(value = "/deleteaccount", method=RequestMethod.GET)
	   public String DeleteAccount(Model model) {
		   return "nav/mypage/DeleteAccount";
		   
		   
	   }
	
	@RequestMapping(value = "/newpwpopup", method=RequestMethod.GET)
	   public String newpwpopup(Model model) {
		   return "nav/mypage/newpwpopup";
		   
	   }
	
	  //비빌번호 변경
	  
	  @RequestMapping(value = "/member_pass_new", method=RequestMethod.POST)
	  
	  @ResponseBody 
	  public String member_pass_new(Model model, @RequestParam String user_id, @RequestParam String password) { 
	  System.out.println(user_id);
	  System.out.println(password);
	  
	  Integer update = mypage.passNew(user_id , password); 
	  System.out.println(update);
	
	 
	  
	 return "nav/mypage/newpwpopup";
	 }
	  
	
	  //회원 정보수정
	  
	  @RequestMapping(value = "/member_info_new", method=RequestMethod.POST)
	  
	  @ResponseBody
	  public String member_info_new(Model model,@RequestParam String user_id, @RequestParam String email, @RequestParam String  addr_ji,@RequestParam String  addr_dong,@RequestParam String phon) {
	  System.out.println(user_id+"아이디");
	  System.out.println(email + "이메일");
	  System.out.println(phon+ "폰");
	  System.out.println(addr_ji+ "주소1");
	  System.out.println(addr_dong+ "주소2");
	  System.out.println("====================");
	  Integer update = mypage.infoNew(user_id,email ,addr_ji,addr_dong, phon);
	 System.out.println(update);
	  
	  
	  
	  return "nav/mypage/mypet_info"; 
	  }
	 

}


	

