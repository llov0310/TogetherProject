package com.together.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.together.domain.EnterpriseVO;

import com.together.domain.MemberVO;
import com.together.domain.ProductVO;
import com.together.service.CustomerService;

import lombok.AllArgsConstructor;



/**
 * Handles requests for the application home page.
 */
@Controller
@AllArgsConstructor
public class HomeController {
	
	private CustomerService customerservice;
	
   //홈 페이지 맵핑
   @RequestMapping(value = "/", method=RequestMethod.GET)
   public String home(Model model) {
	   return "home";
   }
   
   //서비스 페이지 맵핑
   @RequestMapping(value = "/services", method=RequestMethod.GET)
   public String services(Model model) {
	   return "nav/services";
   }
   
   //로그인 페이지 맵핑
   @RequestMapping(value = "/login", method=RequestMethod.GET)
   public String login(Model model) {
	   
	   return "nav/login";
   }
   
   //회원가입 페이지 맵핑
   @RequestMapping(value = "/signup", method=RequestMethod.GET)
   public String signup(Model model) {
	   
	   return "nav/signup";
   }
   
   //호텔, 병원, 장례, 추모게시판 같이 있는 페이지 맵핑
   @RequestMapping(value = "/page", method=RequestMethod.GET)
   public String menuPage(Model model) {
	   
	   return "service/page";
   }
   
   //호텔 페이지 맵핑
   @RequestMapping(value = "/hotel", method=RequestMethod.GET)
   public String hotel(Model model) {
	   
	   return "service/hotel";
   }
   
   //호텔 페이지 관련 이동 맵핑 정보
   @RequestMapping(value = "/hotelserch", method=RequestMethod.GET)
   public String hotelserch(Model model ,HttpServletRequest request,HttpSession session ,@RequestParam String toAddress
		   		,@RequestParam String checkin, @RequestParam String checkout) {
	   
	   
	   ArrayList<EnterpriseVO> result = new ArrayList<EnterpriseVO>();
	   String in = checkin.substring(5);
	   String out = checkout.substring(5);
	   

		String[] AddressList = {"서울","인천","여수","대구","강릉",
		"전주","대전","통영","남해","거제","광주","울산","수원"
		,"평창","춘천","가명","태안","제주도"};

			for(int i=0; i<AddressList.length; i++) {
	
			   if(toAddress.equals(AddressList[i])) {
				   String Address = AddressList[i];
				   result = customerservice.ser(Address,in,out);   
			   }
			   
			 }
			
			if(result.size() == 0) {
				String Serch = toAddress;
				result = customerservice.ser2(Serch,in,out);
			}
		
	   model.addAttribute("etp_list", result);
	   
	  return  "service/hotel/hotelserch";
   }
   
   //펫 장례 페이지 맵핑
   @RequestMapping(value = "/goodbye1", method=RequestMethod.GET)
   public String goodbye1(Model model) {
	   
	   return "service/goodbye1/goodbye1_home";
   }
   
   //추모 게시판 페이지 맵핑
   @RequestMapping(value = "/memorialGallery", method=RequestMethod.GET)
   public String memorialGallery(Model model) {
	   
	   return "service/memorial/memorialGallery";
   }
   
   //마이 페이지 맵핑
   @RequestMapping(value = "/mypage", method=RequestMethod.GET)
   public String mypage(Model model) {
	   
	   return "nav/mypage";
   }
   
   //업체 신청 페이지 맵핑
   @RequestMapping(value = "/etpApply", method=RequestMethod.GET)
   public String etpApply(Model model) {
	   
	   return "nav/etpApply";
   }
 
   
   
  
   
}





//
//package com.together.controller;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//
//import org.apache.ibatis.annotations.Param;
//import org.omg.CORBA.Request;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//
//import com.together.service.HosService;
//
//import lombok.AllArgsConstructor;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//
//
///**
// * Handles requests for the application home page.
// */
//@Controller
//@AllArgsConstructor
//public class HospitalController {
//	
//	private HosService Hos;
//	
//		@RequestMapping(value = "/HospitalLink", method = RequestMethod.GET)
//		public String HosLink(Model model, HttpServletRequest request) {
//			System.out.println("요까이");
//			return "service/hospital/hospital_list";
//		}
//		
//		
//		
//		//병원 페이지 목록 읽어오기
//	   @RequestMapping(value = "/Hospital", method = RequestMethod.GET)
//	   @ResponseBody
//	   public JSONObject HosData(Model model, HttpServletRequest request) {
//	      
//	      BufferedReader br = null;
//	      //String sidoName[] = {"서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주", "세종"};
//	      String sidoName1[] = {"경기도"};
//	      JSONObject json = new JSONObject();
//	      JSONArray ListDataArray = new JSONArray();
//	      JSONArray TotalDataArray = new JSONArray();
//	    
//	      
//	        try{
//	           //경기도권 리스트 받아오기
//	           for(int i=0; i<sidoName1.length; i++) {
//	        	   
//	              String urlstr = " https://openapi.gg.go.kr/Animalhosptl?"
//	                     + "KEY=668375a241224e8da08d4c0493a43d5e"
//	                     + "&Type=json&plndex=1&pSize=50";
//	              
//	                URL url = new URL(urlstr);
//	                
//	                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
//	                
//	                urlconnection.setRequestMethod("GET");
//	                
//	                br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
//	                
//	                String result = "";
//	                String line = "";
//	                while((line = br.readLine()) != null) {
//	                    result = result + line + "\n";
////	                    System.out.println("동물병원 리스트 결과값 : "+result);
//	                    
//	                    JSONObject jsonObj = JSONObject.fromObject(result);
//	                    
//	                    JSONArray NewArray = JSONArray.fromObject(jsonObj.get("Animalhosptl"));
//	               
//	                    ListDataArray.add(NewArray.get(1));
//	                    
//	                }
//	                br.close();
//	                urlconnection.disconnect();
//	               
//	           }
//	           JSONObject testObj = JSONObject.fromObject(ListDataArray.get(0));
//	           
//	           JSONArray testArray = JSONArray.fromObject(testObj.get("row"));
//	           
//
//
//	           for(int i=0;i<testArray.size(); i++)
//	            {
//	                JSONObject jsonObject1 = testArray.getJSONObject(i);
//	                
//	                JSONObject jsonObject2 = new JSONObject();
//	                
//	               
//	               
//	                jsonObject2.put("Dong",jsonObject1.getString("SIGUN_NM"));
//	                jsonObject2.put("NM",jsonObject1.getString("BIZPLC_NM"));
//	                jsonObject2.put("Addr",jsonObject1.getString("REFINE_LOTNO_ADDR"));
//	                
//	                if(jsonObject1.get("LOCPLC_FACLT_TELNO").equals("null")|| jsonObject1.get("LOCPLC_FACLT_TELNO")==null) {
//	                jsonObject2.put("Ph","053-0000-0000");
//	            	}else {
//	            		jsonObject2.put("Ph",jsonObject1.getString("LOCPLC_FACLT_TELNO"));	
//	            	}
////	                
//	                TotalDataArray.add(jsonObject2);
//	               
//	                
//	                
//	            }
//	           json.put("result", TotalDataArray);
////	           System.out.println(json);
//	           
//	           
//	           
//	           
//	        }catch(Exception e){
//	            System.out.println(e.getMessage());
//	        }
//	        System.out.println("실행 다했다...");
//	     
//	      return json;
//	   }
// 
//  
//   
//}
