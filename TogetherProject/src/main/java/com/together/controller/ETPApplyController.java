package com.together.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.together.domain.EnterpriseVO;
import com.together.service.ETPApplyService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ETPApplyController {
	
	private ETPApplyService etpApplyService;
	
	// 업체 등록 
	@RequestMapping(value = "/etpApply", method = RequestMethod.POST)
	public String etpApply(Model model, EnterpriseVO ins, @RequestParam String cd) {
		
		System.out.println(cd);

		if(cd.equals("h")) {
				int insert = etpApplyService.etpApply(ins);
				if (insert != 0) {
					return "nav/etpApply";
				} else {
					return "home";
				}
		
		}else if(cd.equals("f")){
				int insert = etpApplyService.etpApply2(ins);
				
				if (insert != 0) {
					return "nav/etpApply";
				} else {
					return "home";
				}
		}
		
		return "nav/etpApply";
	
	
		// 수정해야 할 부분
		

	}
	
}
