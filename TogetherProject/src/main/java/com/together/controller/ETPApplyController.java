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
	public String etpApply(Model model, EnterpriseVO ins) {
		
		System.out.println(ins + "아ㅏㅏㅏㅏㅏ즈뱅야ㅑㅑㅑㅑㅑ");
		int insert = etpApplyService.etpApply(ins);
		
		
		
		System.out.println(insert+"반환값은?");
		// 수정해야 할 부분
		if (insert != 0) {
			return "nav/etpApply";
		} else {
			return "home";
		}

	}
	
}
