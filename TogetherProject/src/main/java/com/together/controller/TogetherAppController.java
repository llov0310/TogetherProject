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
	
}
