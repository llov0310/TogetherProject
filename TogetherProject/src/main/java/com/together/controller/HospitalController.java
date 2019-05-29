package com.together.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.together.service.HosService;

import lombok.AllArgsConstructor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



/**
 * Handles requests for the application home page.
 */
@Controller
@AllArgsConstructor
public class HospitalController {
	
	private HosService Hos;


		@RequestMapping(value = "/HospitalLink", method = RequestMethod.GET)
		public String HosLink(Model model, HttpServletRequest request) {
			
			return "service/hospital/hospital_list";
		}
		
		
		
		//병원 페이지 목록 읽어오기
	   @RequestMapping(value = "/Hospital", method = RequestMethod.GET)
	   @ResponseBody
	   public JSONObject HosData(Model model, HttpServletRequest request) {
	      
	      BufferedReader br = null;
	   
	      //String sidoName[] = {"서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주", "세종"};
	      String sidoName1[] = {"경기도"};
	      JSONObject json = new JSONObject();
	      JSONArray ListDataArray = new JSONArray();
	      JSONArray TotalDataArray = new JSONArray();
	    
	      
	        try{
	           //경기도권 리스트 받아오기
	           for(int i=0; i<sidoName1.length; i++) {
	        	   
	              String urlstr = " https://openapi.gg.go.kr/Animalhosptl?"
	                     + "KEY=668375a241224e8da08d4c0493a43d5e"
	                     + "&Type=json&plndex=1&pSize=10";
	              
	                URL url = new URL(urlstr);
	                
	                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
	                
	                urlconnection.setRequestMethod("GET");
	                
	                br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
	                
	                String result = "";
	                String line = "";
	                while((line = br.readLine()) != null) {
	                    result = result + line + "\n";
//	                    System.out.println("동물병원 리스트 결과값 : "+result);
	                    
	                    JSONObject jsonObj = JSONObject.fromObject(result);
	                    
	                    JSONArray NewArray = JSONArray.fromObject(jsonObj.get("Animalhosptl"));
	               
	                    ListDataArray.add(NewArray.get(1));
	                    
	                }
	                br.close();
	                urlconnection.disconnect();
	               
	           }
	           JSONObject testObj = JSONObject.fromObject(ListDataArray.get(0));
	           
	           JSONArray testArray = JSONArray.fromObject(testObj.get("row"));
	           


	           for(int i=0;i<testArray.size(); i++)
	            {
	                JSONObject jsonObject1 = testArray.getJSONObject(i);
	                
	                JSONObject jsonObject2 = new JSONObject();
	                
	               
	               
	                jsonObject2.put("Dong",jsonObject1.getString("SIGUN_NM"));
	                jsonObject2.put("NM",jsonObject1.getString("BIZPLC_NM"));
	                jsonObject2.put("Addr",jsonObject1.getString("REFINE_LOTNO_ADDR"));
	                
	                if(jsonObject1.get("LOCPLC_FACLT_TELNO").equals("null")|| jsonObject1.get("LOCPLC_FACLT_TELNO")==null) {
	                jsonObject2.put("Ph","053-0000-0000");
	            	}else {
	            		jsonObject2.put("Ph",jsonObject1.getString("LOCPLC_FACLT_TELNO"));	
	            	}
//	                
	                TotalDataArray.add(jsonObject2);
	               
	                
	                
	            }
	           json.put("result", TotalDataArray);
//	           System.out.println(json);
	           
	           
	           
	           
	        }catch(Exception e){
	            System.out.println(e.getMessage());
	        }
	        System.out.println("실행 다했다...");
	     
	      return json;
	   }
 
  
   
}
