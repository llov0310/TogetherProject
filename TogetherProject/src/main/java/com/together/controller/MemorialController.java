package com.together.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MemorialController {
	
	   @RequestMapping(value = "/writeForm", method=RequestMethod.GET)
	   public String home(Model model) {
		   return "service/memorial/writeForm";
	   }

}