package com.together.controller;

import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.google.protobuf.Empty;
import com.together.domain.EnterpriseVO;
import com.together.domain.HospitalOrdersVO;
import com.together.domain.MemberVO;
import com.together.domain.OrdersVO;
import com.together.domain.PetDiaryVO;
import com.together.domain.ReviewBoardVO;
import com.together.domain.UserOrderVO;
import com.together.service.AppService;

import lombok.AllArgsConstructor;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Controller
@AllArgsConstructor
public class TogetherAppController {

	private AppService App;

	@ResponseBody
	@RequestMapping(value = "/WebSignup", method = RequestMethod.POST)
	public JSONObject WebSignup(@RequestBody String a) {

		String user_info = URLDecoder.decode(a);

		JSONObject Map = new JSONObject();
		Map = JSONObject.fromObject(user_info);

		System.out.println(Map);
		MemberVO ins = new MemberVO();

		ins.setUser_id(Map.optString("user_id"));
		ins.setF_uid(Map.optString("Uid"));
		ins.setUser_nm(Map.optString("username"));
		ins.setPh_no(Map.optString("ph_no"));
		ins.setBirth_dt(Map.optString("birth_dt"));
		ins.setPassword(Map.optString("password"));

		System.out.println(ins);
		int singup = App.signup(ins);

		// 현재 값을 받아오는 작업은 끝내놓음
		JSONObject jobj = new JSONObject(); // 최종적인 반환형태

		if (singup != 0) {
			jobj.put("result", "success");
		} else {
			jobj.put("result", "fail");
		}

		return jobj;

	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public JSONObject home2(@RequestBody String a) {
		System.out.println("test android");
		String decodeResult = URLDecoder.decode(a);

		String result[] = decodeResult.split("=");

		String in = result[0].substring(5);
		String out = result[1].substring(5);
		String Address = result[2];
		String localLat = result[4];
		String localLnt = result[5];

		ArrayList<EnterpriseVO> list = new ArrayList<EnterpriseVO>();

		ArrayList<ReviewBoardVO> review_avg = new ArrayList<ReviewBoardVO>();

		ArrayList<EnterpriseVO> pro_first_list = new ArrayList<EnterpriseVO>();

		// 나중에 저 address가 서울/대구/지역명이면 배열에서 비교받아 아래구문 실행 아니면 다른 sql문 실행
		list = App.hotelList(in, out, Address, localLat, localLnt);

		JSONObject obj = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		JSONArray Arrayobj = new JSONArray();

		for (int i = 0; i < list.size(); i++) {
			map.put("etp_img_path", list.get(i).getEtp_if_img_path());
			map.put("etp_lat", list.get(i).getEtp_lat());
			map.put("etp_lnt", list.get(i).getEtp_lnt());
			map.put("etp_cont", list.get(i).getEtp_content());
			map.put("etp_addr", list.get(i).getEtp_addr());
			map.put("etp_nm", list.get(i).getEtp_nm());
			map.put("etp_if_time1", list.get(i).getEtp_if_time1());
			map.put("etp_if_time2", list.get(i).getEtp_if_time2());
			map.put("etp_km", list.get(i).getKm());

			review_avg = App.reviewcount(list.get(i).getEtp_cd());
			for (int f = 0; f < review_avg.size(); f++) {
				if (review_avg.get(0).getRb_avg() == 0) {
					map.put("avg", 0.0);
				} else {
					map.put("avg", review_avg.get(f).getRb_avg());
				}
			}

			pro_first_list = App.firstproduct(list.get(i).getEtp_cd());

			for (int j = 0; j < pro_first_list.size(); j++) {
				map.put("firstprice", pro_first_list.get(0).getPd_price());
			}

			Arrayobj.add(map);
		}

		System.out.println(Arrayobj);

		JSONArray sortedJsonArray = new JSONArray();

		List<JSONObject> jsonValues = new ArrayList<JSONObject>();
		for (int i = 0; i < Arrayobj.size(); i++) {
			jsonValues.add(Arrayobj.getJSONObject(i));
		}

		if (result[3] != "없음") {

			System.out.println(result[3]);

			if (result[3].trim().equals("거리순")) {
				final String fillter = "etp_km";

				Collections.sort(jsonValues, new Comparator<JSONObject>() {
					// You can change "Name" with "ID" if you want to sort by ID

					@Override
					public int compare(JSONObject a, JSONObject b) {
//					            String valA = new String();
//					            String valB = new String();
						Double valA = new Double((Double) a.get(fillter));
						Double valB = new Double((Double) b.get(fillter));

						return valA.compareTo(valB);
						// if you want to change the sort order, simply use the following:
						// return -valA.compareTo(valB);
					}
				});

			} else if (result[3].trim().equals("가격순")) {
				final String fillter = "firstprice";

				Collections.sort(jsonValues, new Comparator<JSONObject>() {
					// You can change "Name" with "ID" if you want to sort by ID

					@Override
					public int compare(JSONObject a, JSONObject b) {
//					            String valA = new String();
//					            String valB = new String();
						Integer valA = new Integer((int) a.get(fillter));
						Integer valB = new Integer((int) b.get(fillter));

						return valA.compareTo(valB);
						// if you want to change the sort order, simply use the following:
						// return -valA.compareTo(valB);
					}
				});
			} else if (result[3].trim().equals("별점순")) {
				final String fillter = "avg";

				Collections.sort(jsonValues, new Comparator<JSONObject>() {
					// You can change "Name" with "ID" if you want to sort by ID

					@Override
					public int compare(JSONObject a, JSONObject b) {
//					            String valA = new String();
//					            String valB = new String();
						Double valA = new Double((Double) a.get(fillter));
						Double valB = new Double((Double) b.get(fillter));

						return valB.compareTo(valA);
						// if you want to change the sort order, simply use the following:
						// return -valA.compareTo(valB);
					}
				});
			}

		}

		for (int i = 0; i < Arrayobj.size(); i++) {
			sortedJsonArray.add(jsonValues.get(i));
		}

		System.out.println(sortedJsonArray);

		obj.put("result", sortedJsonArray);
//		   System.out.println(obj);

		return obj;
	}

	@ResponseBody
	@RequestMapping(value = "/detail_product", method = RequestMethod.POST)
	public JSONObject Productlist(@RequestBody String a) {
		System.out.println("");
		String decodeResult = URLDecoder.decode(a);

		String result[] = decodeResult.split("=");

		String etpName = result[1];
		String etpAddr = result[0];
		String etpfirst = result[2];
		String etplast = result[3];

		ArrayList<EnterpriseVO> list = new ArrayList<EnterpriseVO>();

		list = App.ProductList(etpName, etpAddr); // 업체 정보

//		   System.out.println(list);
		String etpcode = list.get(0).getEtp_cd();

		ArrayList<EnterpriseVO> product = new ArrayList<EnterpriseVO>();

//		   System.out.println(etpcode+","+etpfirst+","+etplast);

		product = App.StockList(etpcode, etpfirst, etplast); // 상품 리스트

		System.out.println(product);
//		   System.out.println(product); 

		// 업체 리뷰
		ArrayList<ReviewBoardVO> review = new ArrayList<ReviewBoardVO>();

		review = App.reviewList(etpcode);

		JSONObject obj = new JSONObject(); // 최종 object

		// 호텔 정보를 담는 obj
		Map<String, Object> map = new HashMap<String, Object>();

		// Josn으로 호텔 정보 삽입
		JSONObject listobj = new JSONObject();
		listobj.put("etp_ph_no", list.get(0).getEtp_ph_no());
		listobj.put("etp_user", list.get(0).getUser_nm());
		listobj.put("etp_code", list.get(0).getEtp_cd());
		listobj.put("etp_license", list.get(0).getEtp_license_no());
		listobj.put("etp_email", list.get(0).getEtp_email());
		listobj.put("etp_info", list.get(0).getEtp_if_info());
		listobj.put("etp_intro", list.get(0).getEtp_if_intro());

		// 상품 정보를 담는 obj
		JSONArray Arrayobj = new JSONArray();

		Map<String, Object> map2 = new HashMap<String, Object>();

		for (int i = 0; i < product.size(); i++) {
			map2.put("pd_cd", product.get(i).getPd_cd());
			map2.put("pd_nm", product.get(i).getPd_nm());
			map2.put("pd_price", product.get(i).getPd_price());
			map2.put("pd_content", product.get(i).getPd_content());
			map2.put("pd_img_src", product.get(i).getPd_img_path());
			Arrayobj.add(map2);
		}

		// 리뷰 정보를 담는 obj
		JSONArray Arrayobj2 = new JSONArray();

		Map<String, Object> map3 = new HashMap<String, Object>();

		for (int i = 0; i < review.size(); i++) {
			map3.put("user_id", review.get(i).getUser_id());
			map3.put("rb_contents", review.get(i).getRb_contents());
			map3.put("user_nm", review.get(i).getUser_nm());
			map3.put("rb_dt", review.get(i).getRb_dt_char());
			map3.put("rb_avg", review.get(i).getRb_avg());
			Arrayobj2.add(map3);
		}

		map.put("info", listobj);
		map.put("product", Arrayobj);
		map.put("review", Arrayobj2);

		obj.put("result", map);

		return obj;
	}

	@ResponseBody
	@RequestMapping(value = "/order_pro", method = RequestMethod.POST)
	public String order(@RequestBody String a) {
		System.out.println("test android");
		String decodeResult = URLDecoder.decode(a);

		String result[] = decodeResult.split("=");

		System.out.println(result);
		String user = result[0]; // user값
		String productname = result[1]; // 상품이름
		String Address = result[2]; // 업체 주소
		String Etpph = result[3]; // 업체전화번호
		String first = result[4]; // 체크인
		String last = result[5]; // 체크아웃
		String procont = result[6]; // 정보
		String pricesplit[] = result[7].split("원"); // 가격 원자르기
		String price = pricesplit[0];

		// 주문을위해 날짜 계산
		int result_day = App.total_day(first, last);

		int sum = result_day * Integer.parseInt(price);

		// user값 받아오기
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();

		list = App.user_info(user);

		ArrayList<EnterpriseVO> productlist = new ArrayList<EnterpriseVO>();

		productlist = App.product_info(productname, Address, Etpph, procont, price);

		String pd_cd = productlist.get(0).getPd_cd();

		String etp_cd = productlist.get(0).getEtp_cd();

		int order_insert = App.insertOrder(user, pd_cd, first, last, sum);

		// 재고 줄어드는거 update문 추가해야됨

		// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

		if (order_insert != 0) {
			return etp_cd;
		} else {
			return "fail";
		}

	}

	@ResponseBody
	@RequestMapping(value = "/Review", method = RequestMethod.POST)
	public String review(@RequestBody String a) {

		String decodeResult = URLDecoder.decode(a);

		String result[] = decodeResult.split("=");

		String user_id = result[0];
		String code = result[1];
		String starcount = result[2];
		String content = result[3];

		int review_insert = App.reviewadd(user_id, code, starcount, content);

		if (review_insert != 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/Recommend_list", method = RequestMethod.POST)
	public JSONObject Recommend_list(@RequestBody String a) {

		JSONObject jobj = new JSONObject();
		JSONArray jarry = new JSONArray();

		ArrayList<EnterpriseVO> list = new ArrayList<EnterpriseVO>();
		list = App.Recommend_list();

		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("etp_cd", list.get(i).getEtp_cd());
			map.put("etp_addr", list.get(i).getEtp_addr());
			map.put("etp_nm", list.get(i).getEtp_nm());
			map.put("etp_time1", list.get(i).getEtp_if_time1());
			map.put("etp_time2", list.get(i).getEtp_if_time2());
			map.put("etp_image", list.get(i).getEtp_if_img_path());
			map.put("reviewcount", list.get(i).getReviewcount());
			map.put("reviewavg", list.get(i).getReviewavg());
			jarry.add(map);
		}

		jobj.put("result", jarry);

		System.out.println(jobj);

		return jobj;

	}

	@ResponseBody
	@RequestMapping(value = "/funeral_list", method = RequestMethod.POST)
	public JSONObject funeral(@RequestBody String a) {

		String decodeResult = URLDecoder.decode(a);
		String result[] = decodeResult.split("=");

		ArrayList<EnterpriseVO> list = new ArrayList<EnterpriseVO>();

		String day = result[0]; // 날짜
		String[] timeconvert = result[1].split(":"); // 시간
		String time = timeconvert[0] + ":" + "00";
		String location = result[2];// 지역

		String formetData = day + " " + time;

		System.out.println(formetData);
		System.out.println(location);
		list = App.funeralLIst(formetData, location);
		System.out.println(list.size());

		ArrayList<ReviewBoardVO> review_avg = new ArrayList<ReviewBoardVO>();
		ArrayList<EnterpriseVO> pro_first_list = new ArrayList<EnterpriseVO>();

		JSONObject obj = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		JSONArray Arrayobj = new JSONArray();
//			   
		for (int i = 0; i < list.size(); i++) {
			map.put("etp_img_path", list.get(i).getEtp_if_img_path());
			map.put("etp_lat", list.get(i).getEtp_lat());
			map.put("etp_lnt", list.get(i).getEtp_lnt());
			map.put("etp_cont", list.get(i).getEtp_content());
			map.put("etp_addr", list.get(i).getEtp_addr());
			map.put("etp_nm", list.get(i).getEtp_nm());
			map.put("etp_if_time1", list.get(i).getEtp_if_time1());
			map.put("etp_if_time2", list.get(i).getEtp_if_time2());
			map.put("etp_cd", list.get(i).getEtp_cd());

			review_avg = App.reviewcount(list.get(i).getEtp_cd());
			System.out.println(review_avg.size());
			System.out.println(review_avg);
			for (int f = 0; f < review_avg.size(); f++) {
				if (review_avg.get(0).getRb_avg() == 0) {
					map.put("avg", "0.0");
				} else {
					map.put("avg", review_avg.get(f).getRb_avg());
				}
			}

			pro_first_list = App.firstfuneralproduct(list.get(i).getEtp_cd());

			for (int j = 0; j < pro_first_list.size(); j++) {
				map.put("firstprice", pro_first_list.get(0).getPd_price());
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
	@RequestMapping(value = "/Funeral_review", method = RequestMethod.POST)
	public JSONObject funeral_review(@RequestBody String a) {

		System.out.println("장례 리뷰 들어왔습니다.");

		String decodeResult = URLDecoder.decode(a);
		System.out.println(decodeResult);
		ArrayList<ReviewBoardVO> list = new ArrayList<ReviewBoardVO>();

		list = App.funeral_review(decodeResult);
		JSONObject jobj = new JSONObject();

		// 리뷰 정보를 담는 obj
		JSONArray Arrayobj2 = new JSONArray();

		Map<String, Object> map3 = new HashMap<String, Object>();

		for (int i = 0; i < list.size(); i++) {
			map3.put("user_id", list.get(i).getUser_id());
			map3.put("rb_contents", list.get(i).getRb_contents());
			map3.put("user_nm", list.get(i).getUser_nm());
			map3.put("rb_dt", list.get(i).getRb_dt_char());
			map3.put("rb_avg", list.get(i).getRb_avg());
			Arrayobj2.add(map3);
		}

		jobj.put("result", Arrayobj2);
		System.out.println(jobj);

		return jobj;
	}

	@ResponseBody
	@RequestMapping(value = "/FuneralInfo", method = RequestMethod.POST)
	public JSONObject funeralGetList(@RequestBody String a) {

		String decodeResult = URLDecoder.decode(a);
		System.out.println(decodeResult);
		ArrayList<EnterpriseVO> list = new ArrayList<EnterpriseVO>();

		list = App.funeralGetList(decodeResult);
		JSONObject jobj = new JSONObject();

		// 리뷰 정보를 담는 obj
		JSONArray Arrayobj = new JSONArray();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("etp_nm", list.get(0).getEtp_nm());
		map.put("etp_addr", list.get(0).getEtp_addr());
		map.put("etp_ph_no", list.get(0).getEtp_ph_no());
		map.put("etp_email", list.get(0).getEtp_email());
		map.put("etp_lat", list.get(0).getEtp_lat());
		map.put("etp_lnt", list.get(0).getEtp_lnt());
		map.put("etp_info", list.get(0).getEtp_if_info());
		map.put("etp_intro", list.get(0).getEtp_if_intro());
		map.put("etp_time", list.get(0).getEtp_if_time1() + "~" + list.get(0).getEtp_if_time2());
		map.put("etp_user", list.get(0).getUser_nm());
		map.put("etp_img", list.get(0).getEtp_if_img_path());
		map.put("etp_license", list.get(0).getEtp_license_no());

		Arrayobj.add(map);

		jobj.put("result", Arrayobj);
		System.out.println(jobj);

		return jobj;
	}

	@ResponseBody
	@RequestMapping(value = "/Funeral_Detail", method = RequestMethod.POST)
	public JSONObject funeralGetDetail(@RequestBody String a) {

		System.out.println("서블릿요청왓음");

		String decodeResult = URLDecoder.decode(a);
		System.out.println(decodeResult);

		Map<String, Object> map = new HashMap<String, Object>(); // jobj에 담기위한 전체 배열
		JSONObject jobj = new JSONObject(); // 최종적인 반환형태
		JSONArray Arrayobj = new JSONArray(); // 최종 어레이

		// 수의
		ArrayList<EnterpriseVO> list1 = new ArrayList<EnterpriseVO>();
		list1 = App.funeral1(decodeResult);

		// 함
		ArrayList<EnterpriseVO> list2 = new ArrayList<EnterpriseVO>();
		list2 = App.funeral2(decodeResult);

		// 관
		ArrayList<EnterpriseVO> list3 = new ArrayList<EnterpriseVO>();
		list3 = App.funeral3(decodeResult);

		// 화장
		ArrayList<EnterpriseVO> list4 = new ArrayList<EnterpriseVO>();
		list4 = App.funeral4(decodeResult);

		JSONObject jobj1 = new JSONObject(); // 최종적인 반환형태
		Map<String, Object> map1 = new HashMap<String, Object>(); // jobj에 담기위한 전체 배열
		JSONArray Arrayobj1 = new JSONArray();
		for (int i = 0; i < list1.size(); i++) {
			map1.put("etp_img_path", list1.get(i).getPd_img_path());
			map1.put("etp_pd_nm", list1.get(i).getPd_nm());
			map1.put("etp_pd_price", list1.get(i).getPd_price());
			map1.put("etp_pd_content", list1.get(i).getPd_content());
			Arrayobj1.add(map1);
		}
		jobj1.put("수의", Arrayobj1);
		Arrayobj.add(jobj1);

		JSONObject jobj2 = new JSONObject(); // 최종적인 반환형태
		Map<String, Object> map2 = new HashMap<String, Object>(); // jobj에 담기위한 전체 배열
		JSONArray Arrayobj2 = new JSONArray();
		for (int f = 0; f < list2.size(); f++) {
			map2.put("etp_img_path", list2.get(f).getPd_img_path());
			map2.put("etp_pd_nm", list2.get(f).getPd_nm());
			map2.put("etp_pd_price", list2.get(f).getPd_price());
			map2.put("etp_pd_content", list2.get(f).getPd_content());
			Arrayobj2.add(map2);
		}
		jobj2.put("함", Arrayobj2);
		Arrayobj.add(jobj2);

		JSONObject jobj3 = new JSONObject(); // 최종적인 반환형태
		Map<String, Object> map3 = new HashMap<String, Object>(); // jobj에 담기위한 전체 배열
		JSONArray Arrayobj3 = new JSONArray();
		for (int k = 0; k < list3.size(); k++) {
			map3.put("etp_img_path", list3.get(k).getPd_img_path());
			map3.put("etp_pd_nm", list3.get(k).getPd_nm());
			map3.put("etp_pd_price", list3.get(k).getPd_price());
			map3.put("etp_pd_content", list3.get(k).getPd_content());
			Arrayobj3.add(map3);
		}
		jobj3.put("관", Arrayobj3);
		Arrayobj.add(jobj3);

		JSONObject jobj4 = new JSONObject(); // 최종적인 반환형태
		Map<String, Object> map4 = new HashMap<String, Object>(); // jobj에 담기위한 전체 배열
		JSONArray Arrayobj4 = new JSONArray();
		for (int q = 0; q < list4.size(); q++) {
			map4.put("etp_img_path", list4.get(q).getPd_img_path());
			map4.put("etp_pd_nm", list4.get(q).getPd_nm());
			map4.put("etp_pd_price", list4.get(q).getPd_price());
			map4.put("etp_pd_content", list4.get(q).getPd_content());
			Arrayobj4.add(map4);
		}
		jobj4.put("화장", Arrayobj4);
		Arrayobj.add(jobj4);

		jobj.put("result", Arrayobj);

		System.out.println(jobj);

		return jobj;

	}

	@ResponseBody
	@RequestMapping(value = "/funeral_order", method = RequestMethod.POST)
	public JSONObject funeral_orders(@RequestBody String a) {

		System.out.println("주문요청왓음");
		String decodeResult = URLDecoder.decode(a);
		System.out.println(decodeResult);
		JSONObject FuneralMap = new JSONObject();
		FuneralMap = JSONObject.fromObject(decodeResult);

		JSONArray jarry = JSONArray.fromObject(FuneralMap.get("result"));

		for (int i = 0; i < jarry.size(); i++) {
			JSONObject jsonObject = jarry.getJSONObject(i);

			String user_id = jsonObject.optString("user");
			String pro_name = jsonObject.optString("name");
			String price = jsonObject.optString("price");
			String order_day = jsonObject.optString("day") + " " + jsonObject.optString("time");
			String code = jsonObject.optString("code");

			int f_orders = App.f_orders(user_id, pro_name, price, order_day, code);

		}

		// 변수값을 0번째꺼만 받아와도 되기때문에 코드값 선언
		JSONObject jsonObject2 = jarry.getJSONObject(0);
		String user_id = jsonObject2.optString("user");
		String code = jsonObject2.optString("code");
		String order_day = jsonObject2.optString("day") + " " + jsonObject2.optString("time");

		// 현재 값을 받아오는 작업은 끝내놓음
		JSONObject jobj = new JSONObject(); // 최종적인 반환형태
		JSONArray Arrayobj = new JSONArray(); // 최종 어레이

		ArrayList<EnterpriseVO> list = App.resultOrders(order_day, code, user_id);
		for (int f = 0; f < list.size(); f++) {
			Map<String, Object> map = new HashMap<String, Object>(); // map
			map.put("etp_nm", list.get(0).getEtp_nm());
			map.put("order_cd", list.get(0).getOr_cd());
			map.put("or_dt", list.get(0).getChardate());
			map.put("or_dt2", list.get(0).getChardate2());
			map.put("stat", list.get(0).getOr_stat());
			map.put("pro_nm", list.get(f).getPd_nm());
			map.put("th_dt", list.get(0).getCharthisdate());
			map.put("price", list.get(f).getOr_price());
			Arrayobj.add(map);
		}
		jobj.put("result", Arrayobj);

		System.out.println(jobj);
		return jobj;

	}

	@ResponseBody
	@RequestMapping(value = "/Hospital_list", method = RequestMethod.POST)
	public JSONObject Hospital_list(@RequestBody String a) {

		System.out.println("주문요청왓음");
		String decodeResult = URLDecoder.decode(a);
		System.out.println(decodeResult);
		JSONObject HospitalMap = new JSONObject();
		HospitalMap = JSONObject.fromObject(decodeResult);

		JSONArray jarry = JSONArray.fromObject(HospitalMap.get("result"));

		ArrayList<EnterpriseVO> list = new ArrayList<EnterpriseVO>();

			JSONObject jsonObject = jarry.getJSONObject(0);
			String Day = jsonObject.optString("Day");
			String item = jsonObject.optString("category");
			String AddressDefault = jsonObject.optString("Address");
			AddressDefault = AddressDefault.replace("대한민국","");
			AddressDefault = AddressDefault.replace("광역시","");
			AddressDefault = AddressDefault.replace("특별시","");
			String lat = jsonObject.optString("lat");
			String lnt = jsonObject.optString("lnt");
			
			String AddressSplit[] = AddressDefault.split(" ");
			
			String Address = AddressSplit[0].trim()+" "+AddressSplit[1].trim();
			
			list = App.getHospitalList(Day, item, Address,lat,lnt);

			System.out.println(list);

		
		JSONObject jobj = new JSONObject(); // 최종적인 반환형태
		JSONArray Arrayobj = new JSONArray(); // 최종 어레이

		ArrayList<ReviewBoardVO> r_list = new ArrayList<ReviewBoardVO>();

		for (int f = 0; f < list.size(); f++) {
			Map<String, Object> map = new HashMap<String, Object>(); // map
			map.put("etp_cd", list.get(f).getEtp_cd());
			map.put("etp_addr", list.get(f).getEtp_addr());
			map.put("etp_nm", list.get(f).getEtp_nm());
			map.put("etp_lat", list.get(f).getEtp_lat());// 위도
			map.put("etp_lnt", list.get(f).getEtp_lnt()); // 경도
			map.put("if_time1", list.get(f).getEtp_if_time1());
			map.put("if_time2", list.get(f).getEtp_if_time2());
			map.put("etp_img_path", list.get(f).getEtp_if_img_path());
			map.put("etp_content", list.get(f).getEtp_content());
			map.put("etp_km",list.get(f).getKm());

			r_list = App.reviewcount(list.get(f).getEtp_cd());

			for (int r = 0; r < r_list.size(); r++) {
				if (r_list.get(0).getRb_avg() == 0) {
					map.put("avg", "0.0");
				} else {
					map.put("avg", r_list.get(f).getRb_avg());
				}
			}

			Arrayobj.add(map);
		}
		jobj.put("result", Arrayobj);

		System.out.println(jobj);
		return jobj;

	}

	@ResponseBody
	@RequestMapping(value = "/Hospital_detail", method = RequestMethod.POST)
	public JSONObject Hospital_detail(@RequestBody String a) {

		String decodeResult = URLDecoder.decode(a);
		System.out.println(decodeResult);

		// 현재 값을 받아오는 작업은 끝내놓음
		JSONObject jobj = new JSONObject(); // 최종적인 반환형태
		JSONArray Arrayobj = new JSONArray(); // 최종 어레이
		Map<String, Object> map2 = new HashMap<String, Object>(); // map

		JSONArray Arrayobj1 = new JSONArray(); // 최종 어레이
		JSONArray Arrayobj2 = new JSONArray(); // 최종 어레이

		ArrayList<EnterpriseVO> list = App.funeralGetList(decodeResult); // 병원업체 정보 불러오기
		ArrayList<ReviewBoardVO> r_list = App.funeral_review(decodeResult); // 병원업체 리뷰

		System.out.println(list);
		System.out.println(r_list);

		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>(); // map

			map.put("etp_nm", list.get(0).getEtp_nm());
			map.put("etp_addr", list.get(0).getEtp_addr());
			map.put("etp_ph_no", list.get(0).getEtp_ph_no());
			map.put("etp_email", list.get(0).getEtp_email());
			map.put("etp_lat", list.get(0).getEtp_lat());
			map.put("etp_lnt", list.get(0).getEtp_lnt());
			map.put("etp_info", list.get(0).getEtp_if_info());
			map.put("etp_intro", list.get(0).getEtp_if_intro());
			map.put("etp_time", list.get(0).getEtp_if_time1() + "~" + list.get(0).getEtp_if_time2());
			map.put("etp_user", list.get(0).getUser_nm());
			map.put("etp_img", list.get(0).getEtp_if_img_path());
			map.put("etp_license", list.get(0).getEtp_license_no());

			Arrayobj1.add(map);
		}

		map2.put("info", Arrayobj1);

		if (r_list.size() == 0) {
			Map<String, Object> map1 = new HashMap<String, Object>(); // map
			map1.put("user_id", "사용자가 없습니다");
			map1.put("rb_contents", "없음");
			map1.put("user_nm", "없음");
			map1.put("rb_dt", "없음");
			map1.put("rb_avg", "없음");

			Arrayobj2.add(map1);
		} else {

			for (int i = 0; i < r_list.size(); i++) {
				Map<String, Object> map1 = new HashMap<String, Object>(); // map
				map1.put("user_id", r_list.get(i).getUser_id());
				map1.put("rb_contents", r_list.get(i).getRb_contents());
				map1.put("user_nm", r_list.get(i).getUser_nm());
				map1.put("rb_dt", r_list.get(i).getRb_dt_char());
				map1.put("rb_avg", r_list.get(i).getRb_avg());

				Arrayobj2.add(map1);
			}

		}
		map2.put("review", Arrayobj2);

		jobj.put("result", map2);

		System.out.println(jobj);
		return jobj;

	}

	@ResponseBody
	@RequestMapping(value = "/Hospital_timeCheck", method = RequestMethod.POST)
	public JSONObject Hospital_timeCheck(@RequestBody String a) {

		String decodeResult = URLDecoder.decode(a);
		System.out.println(decodeResult);

		String result[] = decodeResult.split("=");

		String etp_nm = result[0];
		String date = result[1];

		ArrayList<EnterpriseVO> list = App.detail_getHosList(etp_nm);

		String etp_cd = list.get(0).getEtp_cd();
		String etp_time1 = list.get(0).getEtp_if_time1();
		String etp_time2 = list.get(0).getEtp_if_time2();
		String time = list.get(0).getEtp_if_hos_time();

		String s_time1 = date + " " + etp_time1;
		String s_time2 = date + " " + etp_time2;

		ArrayList<HospitalOrdersVO> orders = App.hospital_orders(etp_cd, s_time1, s_time2);

		// 현재 값을 받아오는 작업은 끝내놓음
		JSONObject jobj = new JSONObject(); // 최종적인 반환형태
		JSONArray Arrayobj = new JSONArray(); // 최종 어레이

		System.out.println(orders);

		if (orders.isEmpty()) {

			Map<String, Object> map = new HashMap<String, Object>(); // map
			map.put("TimeSet", time);
			map.put("etp_time", etp_time1 + "~" + etp_time2);
			Arrayobj.add(map);

		} else {

			for (int i = 0; i < orders.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>(); // map
				map.put("CheckTime1", orders.get(i).getHor_dt_cf());
				map.put("CheckTime2", orders.get(i).getHor_dt_cl());
				map.put("TimeSet", time);
				map.put("etp_time", etp_time1 + "~" + etp_time2);
				Arrayobj.add(map);
			}
		}

		jobj.put("result", Arrayobj);

		System.out.println(jobj);
		return jobj;

	}

	@ResponseBody
	@RequestMapping(value = "/Hospital_ordersSet", method = RequestMethod.POST)
	public JSONObject Hospital_ordersSet(@RequestBody String a) {

		String decodeResult = URLDecoder.decode(a);

		System.out.println("병원주문왔음");

		JSONObject HospitalMap = new JSONObject();
		HospitalMap = JSONObject.fromObject(decodeResult);
		System.out.println(HospitalMap);

		String user_id = HospitalMap.optString("user_id");
		String TimeSet[] = HospitalMap.optString("Time").split("~");
		String Time = HospitalMap.optString("date") + " " + TimeSet[0];
		String Time2 = HospitalMap.optString("date") + " " + TimeSet[1];
		String petcode = HospitalMap.optString("petcode");
		String etp_nm = HospitalMap.optString("etp_nm");
		String editText = HospitalMap.optString("edit");
		JSONArray CanserArray = HospitalMap.optJSONArray("Canser");

		HospitalOrdersVO InsVO = new HospitalOrdersVO();
		InsVO.setUser_id(user_id);
		InsVO.setEtp_cd(etp_nm); // 서브쿼리를 통해 업체코드를 가져올것임
		InsVO.setHor_pet_cd(petcode);
		InsVO.setHor_detail(editText);
		InsVO.setHor_dt_cf(Time);
		InsVO.setHor_dt_cl(Time2);

		System.out.println(InsVO);
		System.out.println(CanserArray);

		int HospitalOrders = App.HosOrders(InsVO);

		ArrayList<HospitalOrdersVO> getHor_cd = App.getHor_cd(InsVO);
		String Hor_cd = getHor_cd.get(0).getHor_cd();

		if (CanserArray.size() != 0) {

			for (int i = 0; i < CanserArray.size(); i++) {
				JSONObject castJobj = CanserArray.getJSONObject(i);
				String Canser = castJobj.optString("list");
				int HospitalOrder_detail = App.HosDetail(Hor_cd, Canser);
			}

		}

		// 현재 값을 받아오는 작업은 끝내놓음
		JSONObject jobj = new JSONObject(); // 최종적인 반환형태
		jobj.put("result", "success");

		return jobj;

	}

	//////////////////////// 이곳부터 마이페이지 정보 서치 ////////////////////////

	@ResponseBody
	@RequestMapping(value = "/CalendarList", method = RequestMethod.POST)
	public JSONObject CalendarList(@RequestBody String a) {

		String Petcode = URLDecoder.decode(a);

		// 현재날짜식인데 안씀
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		Calendar time = Calendar.getInstance();
		String formetTime = format1.format(time.getTime());
		// 안씀 혹시몰라 남겨둠

		System.out.println(Petcode + "//" + formetTime);
		ArrayList<PetDiaryVO> getCalendarList = new ArrayList<>();

		getCalendarList = App.getCalList(Petcode);

		System.out.println(getCalendarList.size());

		JSONArray jarray = new JSONArray();

		for (int i = 0; i < getCalendarList.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("settime1", getCalendarList.get(i).getPet_dia_settime1_char());
			map.put("settime2", getCalendarList.get(i).getPet_dia_settime2_char());
			map.put("Content", getCalendarList.get(i).getPet_dia_content());
			map.put("Type", getCalendarList.get(i).getPet_dia_type());

			jarray.add(map);
		}

		// 현재 값을 받아오는 작업은 끝내놓음
		JSONObject jobj = new JSONObject(); // 최종적인 반환형태
		jobj.put("result", jarray);

		return jobj;

	}

	@ResponseBody
	@RequestMapping(value = "/Writhcalendar", method = RequestMethod.POST)
	public JSONObject Writhcalendar(@RequestBody String a) {

		String Write = URLDecoder.decode(a);

		JSONObject Map = new JSONObject();
		Map = JSONObject.fromObject(Write);

		System.out.println(Map);
		PetDiaryVO setVO = new PetDiaryVO();

		setVO.setPet_dia_content(Map.optString("content"));
		setVO.setPet_dia_settime1_char(Map.optString("first"));
		setVO.setPet_dia_settime2_char(Map.optString("last"));
		setVO.setUser_id(Map.optString("userUid"));
		setVO.setPet_dia_petcode(Map.optString("petUid"));

		int petinsertDiary = App.insertDiary(setVO);

		// 현재 값을 받아오는 작업은 끝내놓음
		JSONObject jobj = new JSONObject(); // 최종적인 반환형태

		if (petinsertDiary != 0) {
			jobj.put("result", "success");
		} else {
			jobj.put("result", "fail");
		}

		return jobj;

	}
	
	
	@ResponseBody
	@RequestMapping(value = "/UserOrder_list", method = RequestMethod.POST)
	public JSONObject UserOrder_list(@RequestBody String a) {

		String id = URLDecoder.decode(a);
		
		ArrayList<UserOrderVO> Userorder = new ArrayList<>();
		JSONObject jobj = new JSONObject(); // 최종적인 반환형태
		JSONArray jarry = new JSONArray();
		
		Userorder = App.UserOrder_list(id);
		
		for(int i=0; i<Userorder.size(); i++) {
			Map<String,Object> map = new HashMap<>();
			map.put("or_cd",Userorder.get(i).getOr_cd());
			map.put("or_stat",Userorder.get(i).getOr_stat());
			map.put("or_dt",Userorder.get(i).getOr_dt());
			map.put("or_dt2",Userorder.get(i).getOr_dt2());
			map.put("th_dt",Userorder.get(i).getTh_dt());
			map.put("or_price",Userorder.get(i).getOr_price());
			map.put("etp_cd",Userorder.get(i).getEtp_cd());
			map.put("etp_nm",Userorder.get(i).getEtp_nm());
			
			jarry.add(map);
		
		}
		
		jobj.put("result",jarry);
		
		System.out.println(jobj);


		return jobj;

	}

}
