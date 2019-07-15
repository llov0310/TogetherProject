package com.together.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;
import com.together.domain.EnterpriseVO;
import com.together.domain.EnterpriseattachVO;
import com.together.domain.HospitalcategoryVO;
import com.together.domain.MemberVO;
import com.together.service.CustomerService;
import com.together.service.ETPAdminService;
import com.together.service.ETPApplyService;

import lombok.AllArgsConstructor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@AllArgsConstructor
public class ETPApplyController {
	
	private ETPApplyService etpApplyService;

	// 업체 등록 
	@RequestMapping(value = "/etpApply", method = RequestMethod.POST)
	public String etpApply(Model model,HttpServletRequest request,EnterpriseVO ins, @RequestParam String cd, HttpServletResponse response) throws IOException {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		ArrayList<EnterpriseVO> prise = new ArrayList<EnterpriseVO>();
		

//		System.out.println(ins.getEtp_addr());
		System.out.println(ins.getEtp_lat());
		System.out.println(ins.getEtp_lnt());

		
		if(cd.equals("h")) {
			
				int insert = etpApplyService.etpApply(ins);
				prise = etpApplyService.info_select(user_id);
				String code = prise.get(0).getEtp_cd();
				int ins1 = etpApplyService.ent_info(code);
				System.out.println(code);
				System.out.println(ins1);
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
				prise = etpApplyService.info_select(user_id);
				String code = prise.get(0).getEtp_cd();
				int ins2 = etpApplyService.ent_info(code);
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
		else if(cd.equals("d")){
			int insert = etpApplyService.etpApply3(ins);
			prise = etpApplyService.info_select(user_id);
			String code = prise.get(0).getEtp_cd();
			int ins3 = etpApplyService.ent_info(code);
			if (insert != 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('(병원)업체 신청이 완료 되었습니다.'); </script>");
				out.flush();
				
				return "home";
			} else {
				return "nav/etpApply";
			}
	}
		
		
		
		return "home";
	
	
		// 수정해야 할 부분

	}
	
	@RequestMapping(value = "/etpselect", method = RequestMethod.GET)
	public String etpSelect(Model model) {
		return "nav/etpSelect";

	}
	@RequestMapping(value = "/etpApplyhospital", method = RequestMethod.GET)
	public String etpApplyhospital(Model model) {
		return "nav/etpApplyHospital";

	}
	
	@ResponseBody
	@RequestMapping(value = "/etphospital", method = RequestMethod.POST)
	public JSONObject etphospital(EnterpriseVO EnterpriseVO, HospitalcategoryVO HospitalcategoryVO,EnterpriseattachVO EnterpriseattachVO, HttpServletRequest request, HttpServletResponse response
			,@RequestBody String param) {
		
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id(); 	//세선의 아이디를 작성자로 사용하기 위해서
		
		ArrayList<EnterpriseVO> prise = new ArrayList<EnterpriseVO>();	
		Map<String, Integer> map = new HashMap<String, Integer>(); //  jsonobject 만들용
		List<Map<String,Object>> paymentMap = new ArrayList<Map<String,Object>>();
		paymentMap = JSONArray.fromObject(param);
		System.out.println(paymentMap);
		
		EnterpriseVO.setEtp_nm((String) paymentMap.get(0).get("etp_nm")); // VO형태로 집어넣고싶으면 SET해서 다 설정해줄것
		EnterpriseVO.setEtp_license_no((String) paymentMap.get(0).get("etp_license_no"));
		EnterpriseVO.setEtp_ph_no((String) paymentMap.get(0).get("etp_ph_no"));
		EnterpriseVO.setEtp_email((String) paymentMap.get(0).get("etp_email"));
		EnterpriseVO.setEtp_addr((String) paymentMap.get(0).get("etp_addr"));
		EnterpriseVO.setEtp_content((String) paymentMap.get(0).get("etp_content"));
		EnterpriseVO.setEtp_lnt((String) paymentMap.get(0).get("etp_lnt"));
		EnterpriseVO.setEtp_lat((String) paymentMap.get(0).get("etp_lat"));
		
		
		JSONArray jarry =  (JSONArray) paymentMap.get(0).get("category"); // 선택한 카테고리가 잇음
		
				
		System.out.println(EnterpriseVO);
		System.out.println(jarry);
		EnterpriseVO.setUser_id(user_id);//회원아이디추가
//		System.out.println(EnterpriseVO);
		int checkNum = etpApplyService.etpApply3(EnterpriseVO); //  checkNum 이 1이면 성공  0이면 실패 
		System.out.println(checkNum);	
		prise = etpApplyService.info_select(user_id);
		System.out.println(prise);
		String code = prise.get(0).getEtp_cd();
		System.out.println(code);

		int ins3 = etpApplyService.ent_info(code);
//		System.out.println(ins3);
		System.out.println("왔다");
		
	
		
		for(int i=0; i<jarry.size(); i++) {
			HospitalcategoryVO.setEtp_cd(code);	
			HospitalcategoryVO.setEtp_ct((String)jarry.get(i));
			int ok = etpApplyService.ent_category(HospitalcategoryVO);
	
			System.out.println(HospitalcategoryVO);
		
		}
		EnterpriseattachVO.setEtp_cd(code);
		EnterpriseattachVO.setEtp_ex_path((String)paymentMap.get(0).get("fileButton"));
		System.out.println(EnterpriseattachVO);
		int upimg = etpApplyService.ent_attach(EnterpriseattachVO);
		
		JSONObject json = JSONObject.fromObject(map);
		map.put("result", ins3);
		System.out.println(json);
		
		return json;
	}
}
