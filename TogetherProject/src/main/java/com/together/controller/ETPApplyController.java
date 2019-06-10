package com.together.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;
import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.service.CustomerService;
import com.together.service.ETPAdminService;
import com.together.service.ETPApplyService;
import com.together.service.ETPManageService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ETPApplyController {
	
	private ETPApplyService etpApplyService;
	private CustomerService customerservice;
	private ETPAdminService etpAdminService;
	
	// 주소 값의 위도, 경도 값을 가져오기 위한 메소드 선언
	
	// 업체 등록 
	@RequestMapping(value = "/etpApply", method = RequestMethod.POST)
	public String etpApply(Model model,HttpServletRequest request,EnterpriseVO ins, @RequestParam String cd, HttpServletResponse response) throws IOException {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		ArrayList<EnterpriseVO> prise = new ArrayList<EnterpriseVO>();
		
		System.out.println(ins.getEtp_addr());
				
		
		
		if(cd.equals("h")) {
			
				int insert = etpApplyService.etpApply(ins);
				prise = etpAdminService.info_select(user_id);
				String code = prise.get(0).getEtp_cd();
				int ins1 = customerservice.ent_info(code);
				if (insert != 0) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('(호텔)업체 신청이 완료 되었습니다.'); </script>");
					out.flush();
					
					return "home";
				} else {
					return "nav/etpApply";
				}
		
		}else if(cd.equals("f")){
				int insert = etpApplyService.etpApply2(ins);
				prise = etpAdminService.info_select(user_id);
				String code = prise.get(0).getEtp_cd();
				int ins2 = customerservice.ent_info(code);
				if (insert != 0) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('(장례)업체 신청이 완료 되었습니다.'); </script>");
					out.flush();
					
					return "home";
				} else {
					return "nav/etpApply";
				}
		}
//		else if(cd.equals("d")){
//			int insert = etpApplyService.etpApply3(ins);
//			prise = etpAdminService.info_select(user_id);
//			String code = prise.get(0).getEtp_cd();
//			int ins3 = customerservice.ent_info(code);
//			if (insert != 0) {
//				response.setContentType("text/html; charset=UTF-8");
//				PrintWriter out = response.getWriter();
//				out.println("<script>alert('(병원)업체 신청이 완료 되었습니다.'); </script>");
//				out.flush();
//				
//				return "home";
//			} else {
//				return "nav/etpApply";
//			}
//	}
		
		
		
		return "home";
	
	
		// 수정해야 할 부분
		

	}
	
}
