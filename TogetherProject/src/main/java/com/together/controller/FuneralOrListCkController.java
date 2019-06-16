package com.together.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.together.service.ETPAdminService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class FuneralOrListCkController { //장례 주문 리스트 확인 전용 컨트롤러
	
	private ETPAdminService etpAdminService;
	
	// 장례 업체 관리자 - 주문 확인 누를 시 상태 업데이트
	@RequestMapping(value = "/etpFuneralOrderListCheck", method = RequestMethod.POST)
	@ResponseBody
	public String etpFuneralOrderListCheck(@RequestBody String param, HttpSession session) {
		
		System.out.println(param);
		List<Map<String,Object>> paymentMap = new ArrayList<Map<String,Object>>();
	    paymentMap = JSONArray.fromObject(param);
	    
	    System.out.println(paymentMap);
	    
	    for(int i=0; i<paymentMap.size(); i++) {
	    	JSONObject jobj = (JSONObject) paymentMap.get(i);

	    	String user_id = (String) jobj.get("user_id");
	    	String or_dt = (String) jobj.get("s_or_dt1");
	    	String or_dt2 = (String) jobj.get("s_or_dt2");
	    	String th_dt = (String) jobj.get("s_th_dt");
	    	
	    	int or_check = etpAdminService.or_checkUpdate(user_id,or_dt,or_dt2,th_dt);
	    
	    }
	    

		return "success";
	}
	
	
}
