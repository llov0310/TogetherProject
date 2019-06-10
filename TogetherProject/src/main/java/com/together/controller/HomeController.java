package com.together.controller;

import java.io.IOException;
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

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;
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
	   
	   System.out.println("홈이 실행되었음");
	   
	   String location = "경기도 성남시 분당구";
	   
	   Float[] coords = geoCoding(location);
	   
//	   System.out.println(code[0]+code[1]);
	   
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
 
   public static Float[] geoCoding(String location) {

	   
	   System.out.println("호출이 되긴되었음");
	   
	   System.out.println(location);
	   
	   if (location == null)  
	   return null;
	   
	   Geocoder geocoder = new Geocoder();

	   System.out.println("null 이 아니어서 여기까지 내려옴");
	   // setAddress : 변환하려는 주소 (경기도 성남시 분당구 등)

	   // setLanguate : 인코딩 설정

	   GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(location).setLanguage("ko").getGeocoderRequest();

	   System.out.println(geocoderRequest);
	   System.out.println("지오코더리퀘스트 설정을햇음");
	   
	   GeocodeResponse geocoderResponse;

	   try {
		   
	   geocoderResponse = geocoder.geocode(geocoderRequest);
	   System.out.println("try안에 들어와 리스폰스 받음");
	   System.out.println(geocoderResponse);
	   
	   if (geocoderResponse.getStatus() == GeocoderStatus.OK & !geocoderResponse.getResults().isEmpty()) {

		   System.out.println("if문 조건에 맞아서 들어옴");
		   
	   GeocoderResult geocoderResult=geocoderResponse.getResults().iterator().next();

	   LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();

	   Float[] coords = new Float[2];

	   coords[0] = latitudeLongitude.getLat().floatValue();

	   coords[1] = latitudeLongitude.getLng().floatValue();

	   return coords;

	   }

	   } catch (IOException ex) {

	   ex.printStackTrace();

	   }

	   return null;

	 }
   
  
   
}



