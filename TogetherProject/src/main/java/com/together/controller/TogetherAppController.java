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
import com.together.domain.MemberVO;
import com.together.domain.ReviewBoardVO;
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
		   
		   ArrayList<ReviewBoardVO> review_avg = new ArrayList<ReviewBoardVO>();
		   
		   ArrayList<EnterpriseVO> pro_first_list = new ArrayList<EnterpriseVO>();
		   
		   list = App.hotelList(in,out,Address);
		   
		   
		   JSONObject obj = new JSONObject();
		   Map<String,Object> map = new HashMap<String,Object>(); 
		   JSONArray Arrayobj = new JSONArray();
		   
		   for(int i=0; i<list.size(); i++) { 
		   map.put("etp_img_path",list.get(i).getEtp_if_img_path());
		   map.put("etp_lat",list.get(i).getEtp_lat());
		   map.put("etp_lnt",list.get(i).getEtp_lnt());
		   map.put("etp_cont",list.get(i).getEtp_content());
		   map.put("etp_addr",list.get(i).getEtp_addr());
		   map.put("etp_nm",list.get(i).getEtp_nm());
		   map.put("etp_if_time1",list.get(i).getEtp_if_time1());
		   map.put("etp_if_time2",list.get(i).getEtp_if_time2());
		   
		   review_avg = App.reviewcount(list.get(i).getEtp_cd());
		   	for(int f=0; f<review_avg.size(); f++) {
		   		if(review_avg.get(0).getRb_avg() == 0) {
		   			map.put("avg","0.0");
		   		}else {
		   		map.put("avg",review_avg.get(f).getRb_avg());
		   		}
		   	}
		   	
		   pro_first_list = App.firstproduct(list.get(i).getEtp_cd());
		   	
		   for(int j=0; j<pro_first_list.size(); j++) {
			   map.put("firstprice",pro_first_list.get(0).getPd_price());
		   }
		   
		   Arrayobj.add(map);
		   }
		   
		   System.out.println(Arrayobj);
		 
		   
		   obj.put("result", Arrayobj);
//		   System.out.println(obj);

		   
		   return obj;
	   }
	
	
	
	@ResponseBody
	   @RequestMapping(value = "/detail_product", method=RequestMethod.POST)
	   public JSONObject Productlist(@RequestBody String a) {
		   System.out.println("");
		   String decodeResult = URLDecoder.decode(a);

		   String result[] = decodeResult.split("=");
		   
		   	String etpName = result[1];
		   	String etpAddr = result[0];
		   	String etpfirst = result[2];
		   	String etplast = result[3];
		   	
//		   	System.out.println(decodeResult);
		   	
		   	
		   ArrayList<EnterpriseVO> list = new ArrayList<EnterpriseVO>();
		   
		   list = App.ProductList(etpName,etpAddr); //업체 정보
		   
//		   System.out.println(list);
		   String etpcode = list.get(0).getEtp_cd();
		   
		   ArrayList<EnterpriseVO> product = new ArrayList<EnterpriseVO>();
		   
		   product = App.StockList(etpcode,etpfirst,etplast); //상품 리스트
		   
//		   System.out.println(product); 
		   
		   //업체 리뷰
		   ArrayList<ReviewBoardVO> review = new ArrayList<ReviewBoardVO>();
		   
		   review = App.reviewList(etpcode);
		   
		   
		   
		   JSONObject obj = new JSONObject(); // 최종 object
		   
		   
		   //호텔 정보를 담는 obj
		   Map<String,Object> map = new HashMap<String,Object>(); 
		   
		   
		   //Josn으로 호텔 정보 삽입
		   JSONObject listobj = new JSONObject();
		   listobj.put("etp_ph_no",list.get(0).getEtp_ph_no());
		   listobj.put("etp_user",list.get(0).getUser_nm());
		   listobj.put("etp_code",list.get(0).getEtp_cd());
		   listobj.put("etp_license",list.get(0).getEtp_license_no());
		   listobj.put("etp_email",list.get(0).getEtp_email());
		   listobj.put("etp_info",list.get(0).getEtp_if_info());
		   listobj.put("etp_intro",list.get(0).getEtp_if_intro());
		   
	
		   
		   //상품 정보를 담는 obj
		   JSONArray Arrayobj = new JSONArray();
		   
		   Map<String,Object> map2 = new HashMap<String,Object>();
		   
		   for(int i=0; i<product.size(); i++) { 
			   map2.put("pd_cd",product.get(i).getPd_cd());
			   map2.put("pd_nm",product.get(i).getPd_nm());
			   map2.put("pd_price", product.get(i).getPd_price());
			   map2.put("pd_content",product.get(i).getPd_content());
			   map2.put("pd_img_src",product.get(i).getPd_img_path());
			   Arrayobj.add(map2);
			}
		   
		   //리뷰 정보를 담는 obj
		   JSONArray Arrayobj2 = new JSONArray();
		   
		   Map<String,Object> map3 = new HashMap<String,Object>();
		   
		   for(int i=0; i<review.size(); i++) {
			   map3.put("user_id",review.get(i).getUser_id());
			   map3.put("rb_contents",review.get(i).getRb_contents());
			   map3.put("user_nm",review.get(i).getUser_nm());
			   map3.put("rb_dt",review.get(i).getRb_dt_char());
			   map3.put("rb_avg",review.get(i).getRb_avg());
			   Arrayobj2.add(map3);
			}
		   
		   
		   map.put("info",listobj);
		   map.put("product",Arrayobj);
		   map.put("review",Arrayobj2);
		   
		   
		   obj.put("result",map);

		   
		   return obj;
	   }
	
		@ResponseBody
	   @RequestMapping(value = "/order_pro", method=RequestMethod.POST)
	   public String order(@RequestBody String a) {
		   System.out.println("test android");
		   String decodeResult = URLDecoder.decode(a);

		   String result[] = decodeResult.split("=");
		   
		   
		  
		   String user = result[0];  // user값
		   String productname = result[1]; // 상품이름
		   String Address = result[2]; //업체 주소
		   String Etpph = result[3]; // 업체전화번호
		   String first = result[4]; // 체크인
		   String last = result[5]; // 체크아웃
		   String procont = result[6]; // 정보
		   String pricesplit[] = result[7].split("원"); // 가격 원자르기
		   String price = pricesplit[0];
		   
		   //주문을위해 날짜 계산
		   int result_day = App.total_day(first,last);
		   
		   
		   int sum = result_day * Integer.parseInt(price);
		   
		   
		   
		   //user값 받아오기		   
		   ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		   list = App.user_info(user);
		   
		   String user_ph = list.get(0).getPh_no();
		   String user_nm = list.get(0).getUser_nm();
		   
		   ArrayList<EnterpriseVO> productlist = new ArrayList<EnterpriseVO>(); 
		  
		   productlist = App.product_info(productname,Address,Etpph,procont,price);

		   String pd_cd = productlist.get(0).getPd_cd();
		   
		   String etp_cd = productlist.get(0).getEtp_cd();
		  
		   int order_insert = App.insertOrder(user,pd_cd,first,last,sum);
		   
		   //재고 줄어드는거 update문 추가해야됨
		   
		   //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
		   
		   
		   if(order_insert != 0) {
			   return etp_cd;   
		   }else {
			   return "fail"; 
		   }
		   
	   }
	      
		@ResponseBody
		   @RequestMapping(value = "/Review", method=RequestMethod.POST)
		   public String review(@RequestBody String a) {
			
			String decodeResult = URLDecoder.decode(a);

			   String result[] = decodeResult.split("=");
			   
			   String user_id = result[0];
			   String code = result[1];
			   String starcount = result[2];
			   String content = result[3];
			   
			   int review_insert = App.reviewadd(user_id,code,starcount,content);
		
			   if(review_insert != 0) {
			   return "success";
			   }else {
				   return "fail";
			   }
		}
		
		
		@ResponseBody
		   @RequestMapping(value = "/funeral_list", method=RequestMethod.POST)
		   public JSONObject funeral(@RequestBody String a) {
			
			String decodeResult = URLDecoder.decode(a);
			String result[] = decodeResult.split("=");
			
			ArrayList<EnterpriseVO> list = new ArrayList<EnterpriseVO>();
			
			   
			   String day = result[0]; // 날짜
			   String[] timeconvert = result[1].split(":"); //시간
			   String time = timeconvert[0]+":"+"00";
			   String[] locationconvert = result[2].split(":"); //지역
			   String location = locationconvert[1].trim();
			   
		
			   
			   String formetData = day + " " + time;
			   
			   System.out.println(formetData);
			   System.out.println(location);
			   list = App.funeralLIst(formetData,location);
			   System.out.println(list.size());
			   
			   ArrayList<ReviewBoardVO> review_avg = new ArrayList<ReviewBoardVO>();
			   ArrayList<EnterpriseVO> pro_first_list = new ArrayList<EnterpriseVO>();
			   
			   JSONObject obj = new JSONObject();
			   Map<String,Object> map = new HashMap<String,Object>(); 
			   JSONArray Arrayobj = new JSONArray();
//			   
			   for(int i=0; i<list.size(); i++) { 
			   map.put("etp_img_path",list.get(i).getEtp_if_img_path());
			   map.put("etp_lat",list.get(i).getEtp_lat());
			   map.put("etp_lnt",list.get(i).getEtp_lnt());
			   map.put("etp_cont",list.get(i).getEtp_content());
			   map.put("etp_addr",list.get(i).getEtp_addr());
			   map.put("etp_nm",list.get(i).getEtp_nm());
			   map.put("etp_if_time1",list.get(i).getEtp_if_time1());
			   map.put("etp_if_time2",list.get(i).getEtp_if_time2());	
			   map.put("etp_cd",list.get(i).getEtp_cd());
			   
			   review_avg = App.reviewcount(list.get(i).getEtp_cd());
			   System.out.println(review_avg.size());
			   System.out.println(review_avg);
			   	for(int f=0; f<review_avg.size(); f++) {
			   		if(review_avg.get(0).getRb_avg() == 0) {
			   			map.put("avg","0.0");
			   		}else {
			   		map.put("avg",review_avg.get(f).getRb_avg());
			   		}
			   	}
			   	
			   	pro_first_list = App.firstfuneralproduct(list.get(i).getEtp_cd());
			   	
				   for(int j=0; j<pro_first_list.size(); j++) {
					   map.put("firstprice",pro_first_list.get(0).getPd_price());
				   }
			   	
//			   
			   Arrayobj.add(map);
			   }
//			   
			   System.out.println(Arrayobj);
//			 
//			   
			   obj.put("result", Arrayobj);
////			   System.out.println(obj);
			   
				  
			   return obj;
			   
		}
		
		@ResponseBody
		   @RequestMapping(value = "/Funeral_review", method=RequestMethod.POST)
		   public JSONObject funeral_review(@RequestBody String a) {
			
			String decodeResult = URLDecoder.decode(a);
			System.out.println(decodeResult);
			ArrayList<ReviewBoardVO> list = new ArrayList<ReviewBoardVO>();
			
			
			list = App.funeral_review(decodeResult);
			JSONObject jobj = new JSONObject();
			
			//리뷰 정보를 담는 obj
			   JSONArray Arrayobj2 = new JSONArray();
			   
			   Map<String,Object> map3 = new HashMap<String,Object>();
			   
			   for(int i=0; i<list.size(); i++) {
				   map3.put("user_id",list.get(i).getUser_id());
				   map3.put("rb_contents",list.get(i).getRb_contents());
				   map3.put("user_nm",list.get(i).getUser_nm());
				   map3.put("rb_dt",list.get(i).getRb_dt_char());
				   map3.put("rb_avg",list.get(i).getRb_avg());
				   Arrayobj2.add(map3);
				}
			   
			   jobj.put("result",Arrayobj2);
			   System.out.println(jobj);
			
			return jobj;
		}
		
		
		@ResponseBody
		   @RequestMapping(value = "/FuneralInfo", method=RequestMethod.POST)
		   public JSONObject funeralGetList(@RequestBody String a) {
			
			String decodeResult = URLDecoder.decode(a);
			System.out.println(decodeResult);
			ArrayList<EnterpriseVO> list = new ArrayList<EnterpriseVO>();
			
			
			list = App.funeralGetList(decodeResult);
			JSONObject jobj = new JSONObject();
			
			//리뷰 정보를 담는 obj
			   JSONArray Arrayobj = new JSONArray();
			   
			   Map<String,Object> map = new HashMap<String,Object>();
			   
			   
				   map.put("etp_nm",list.get(0).getEtp_nm());
				   map.put("etp_addr",list.get(0).getEtp_addr());
				   map.put("etp_ph_no",list.get(0).getEtp_ph_no());
				   map.put("etp_email",list.get(0).getEtp_email());
				   map.put("etp_lat",list.get(0).getEtp_lat());
				   map.put("etp_lnt",list.get(0).getEtp_lnt());
				   map.put("etp_info",list.get(0).getEtp_if_info());
				   map.put("etp_intro",list.get(0).getEtp_if_intro());
				   map.put("etp_time",list.get(0).getEtp_if_time1() + "~" + list.get(0).getEtp_if_time2());
				   map.put("etp_user",list.get(0).getUser_nm());
				   map.put("etp_img",list.get(0).getEtp_if_img_path());
				   map.put("etp_license",list.get(0).getEtp_license_no());
				   
				   Arrayobj.add(map);
				
			   
			   jobj.put("result",Arrayobj);
			   System.out.println(jobj);
			
			   
			return jobj;
		}
		
		@ResponseBody
		   @RequestMapping(value = "/Funeral_Detail", method=RequestMethod.POST)
		   public JSONObject funeralGetDetail(@RequestBody String a) {
		
			
			System.out.println("서블릿요청왓음");
			
			String decodeResult = URLDecoder.decode(a);
			System.out.println(decodeResult);

			Map<String,Object> map = new HashMap<String,Object>(); // jobj에 담기위한 전체 배열
			JSONObject jobj = new JSONObject(); //최종적인 반환형태
			JSONArray Arrayobj = new JSONArray(); //최종 어레이
			
			//수의
			ArrayList<EnterpriseVO> list1 = new ArrayList<EnterpriseVO>();
			list1 = App.funeral1(decodeResult);
			
			//함
			ArrayList<EnterpriseVO> list2 = new ArrayList<EnterpriseVO>();
			list2 = App.funeral2(decodeResult);
			
			//관
			ArrayList<EnterpriseVO> list3 = new ArrayList<EnterpriseVO>();
			list3 = App.funeral3(decodeResult);
			
			//화장
			ArrayList<EnterpriseVO> list4 = new ArrayList<EnterpriseVO>();
			list4 = App.funeral4(decodeResult);
			
			
			JSONObject jobj1 = new JSONObject(); //최종적인 반환형태
			Map<String,Object> map1 = new HashMap<String,Object>(); // jobj에 담기위한 전체 배열
			JSONArray Arrayobj1 = new JSONArray();
			for(int i=0; i<list1.size(); i++) { 
				   map1.put("etp_img_path",list1.get(i).getPd_img_path());
				   map1.put("etp_pd_nm",list1.get(i).getPd_nm());
				   map1.put("etp_pd_price",list1.get(i).getPd_price());
				   map1.put("etp_pd_content",list1.get(i).getPd_content());
				   Arrayobj1.add(map1);
			}
			jobj1.put("수의",Arrayobj1);
			Arrayobj.add(jobj1);
			
			
			JSONObject jobj2 = new JSONObject(); //최종적인 반환형태
			Map<String,Object> map2 = new HashMap<String,Object>(); // jobj에 담기위한 전체 배열
			JSONArray Arrayobj2 = new JSONArray();
			for(int f=0; f<list2.size(); f++) {
				   map2.put("etp_img_path",list2.get(f).getPd_img_path());
				   map2.put("etp_pd_nm",list2.get(f).getPd_nm());
				   map2.put("etp_pd_price",list2.get(f).getPd_price());
				   map2.put("etp_pd_content",list2.get(f).getPd_content());
				   Arrayobj2.add(map2);
			}
			jobj2.put("함",Arrayobj2);
			Arrayobj.add(jobj2);
			
			
			JSONObject jobj3 = new JSONObject(); //최종적인 반환형태
			Map<String,Object> map3 = new HashMap<String,Object>(); // jobj에 담기위한 전체 배열
			JSONArray Arrayobj3 = new JSONArray();
			for(int k=0; k<list3.size(); k++) {
				   map3.put("etp_img_path",list3.get(k).getPd_img_path());
				   map3.put("etp_pd_nm",list3.get(k).getPd_nm());
				   map3.put("etp_pd_price",list3.get(k).getPd_price());
				   map3.put("etp_pd_content",list3.get(k).getPd_content());
				   Arrayobj3.add(map3);
			}
			jobj3.put("관",Arrayobj3);
			Arrayobj.add(jobj3);
			
			JSONObject jobj4 = new JSONObject(); //최종적인 반환형태
			Map<String,Object> map4 = new HashMap<String,Object>(); // jobj에 담기위한 전체 배열
			JSONArray Arrayobj4 = new JSONArray();
			for(int q=0; q<list4.size(); q++) {
				   map4.put("etp_img_path",list4.get(q).getPd_img_path());
				   map4.put("etp_pd_nm",list4.get(q).getPd_nm());
				   map4.put("etp_pd_price",list4.get(q).getPd_price());
				   map4.put("etp_pd_content",list4.get(q).getPd_content());
				   Arrayobj4.add(map4);
			}
			jobj4.put("화장",Arrayobj4);
			Arrayobj.add(jobj4);
			
			
			jobj.put("result",Arrayobj);
			
				   
			
			System.out.println(jobj);
			
			
			return jobj;
			
		
		}
		
		
	
	
}
