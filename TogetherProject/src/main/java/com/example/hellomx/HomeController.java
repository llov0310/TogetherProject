package com.example.hellomx;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.hellomx.domain.MemberVO;
import com.example.hellomx.service.CustomerService;

import lombok.AllArgsConstructor;



/**
 * Handles requests for the application home page.
 */
@Controller
@AllArgsConstructor
public class HomeController {
	
   private CustomerService customerService;

   @RequestMapping(value = "/", method=RequestMethod.GET)
   public String home(Model model) {
	   return "home";
   }
   
   @RequestMapping(value = "/services", method=RequestMethod.GET)
   public String services(Model model) {
	   return "nav/services";
   }

   //▼▼▼▼▼▼▼로그인을 위한 맵핑▼▼▼▼▼▼▼
   @RequestMapping(value = "/login", method=RequestMethod.GET)
   public String login(Model model) {
	   
	   return "nav/login";
   }
   
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO userInfo, HttpSession session, Model model) {
		
		MemberVO user = customerService.login(userInfo.getUserid(), userInfo.getPassword());
		
		
		if (user != null) {
			session.setAttribute("user", user);
			return "home";
		} else {
			model.addAttribute("error", "login failed");
			return "login";
		}
		
		
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String loginForm(HttpSession session) {
		session.invalidate();
		return "home";
	}
	//▲▲▲▲▲▲▲로그인을 위한 맵핑▲▲▲▲▲▲▲
	
   @RequestMapping(value = "/sign", method = RequestMethod.POST)
   public String signup(Model model, MemberVO ins) {
	   
	   int insert = customerService.signup(ins);
	  
	   if(insert != 0) {
		   return "nav/login";
	   }else {
		   return "home";
	   }
	 
	   
	   
   }
   
   
   
   @RequestMapping(value = "/signup", method=RequestMethod.GET)
   public String signup(Model model) {
	   
	   return "nav/signup";
   }
   
   @RequestMapping(value = "/page", method=RequestMethod.GET)
   public String menuPage(Model model) {
	   
	   return "service/page";
   }
   
   //각 기능 페이지 이동 Mapping
   
   @RequestMapping(value = "/hotel", method=RequestMethod.GET)
   public String hotel(Model model) {
	   
	   return "service/hotel";
   }
   
   @RequestMapping(value = "/hospital", method=RequestMethod.GET)
   public String hospital(Model model) {
	   
	   return "service/hospital";
   }
   @RequestMapping(value = "/goodbye1", method=RequestMethod.GET)
   public String goodbye1(Model model) {
	   
	   return "service/goodbye1/goodbye1_home";
   }
   
   @RequestMapping(value = "/goodbye2", method=RequestMethod.GET)
   public String goodbye2(Model model) {
	   
	   return "service/goodbye2";
   }
   
   
   

		

	
}
