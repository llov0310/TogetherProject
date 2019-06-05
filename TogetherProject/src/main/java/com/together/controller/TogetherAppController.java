package com.together.controller;


import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.together.domain.EnterpriseVO;
import com.together.service.AppService;

import lombok.AllArgsConstructor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@AllArgsConstructor
public class TogetherAppController {

	
	private AppService App;
	
	@ResponseBody
	   @RequestMapping(value = "/test", method=RequestMethod.POST)
	   public JSONObject home2(@RequestBody String a) {
		   System.out.println("test android");
		   String decodeResult = URLDecoder.decode(a);

		   String result[] = decodeResult.split("=");
		   
		  
		   
		   String in = result[0].substring(5);
		   String out = result[1].substring(5);
		   String Address = result[2];
		   
		   ArrayList<EnterpriseVO> list = new ArrayList<EnterpriseVO>();
		   
		   list = App.hotelList(in,out,Address);
		   
		   
		   JSONObject obj = new JSONObject();
		   Map<String,String> map = new HashMap<String,String>(); 
		   JSONArray Arrayobj = new JSONArray();
		   
		   for(int i=0; i<list.size(); i++) { 
		   map.put("etp_addr",list.get(i).getEtp_addr());
		   map.put("etp_nm",list.get(i).getEtp_nm());
		   map.put("etp_if_time1",list.get(i).getEtp_if_time1());
		   map.put("etp_if_time2",list.get(i).getEtp_if_time2());
		   Arrayobj.add(map);
		   }
		   
		   obj.put("result", Arrayobj);
		   System.out.println(obj);

		   
		   return obj;
	   }
	   
	      
	
	
}
