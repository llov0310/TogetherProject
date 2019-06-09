package com.together.service;

import java.util.ArrayList;

import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;

public interface AppService {

	
	//앱 호텔 리스트 불러오기
	public ArrayList<EnterpriseVO> hotelList(String in, String out, String address);
	
	//앱 지정 호텔 정보 
	public ArrayList<EnterpriseVO> ProductList(String etpName, String etpAddr);

	//앱 지정 호텔 상품
	public ArrayList<EnterpriseVO> StockList(String etpcode, String etpfirst, String etplast);

	//접속된 유저값 가져오기
	public ArrayList<MemberVO> user_info(String user);

	//상품 리스트 가져오기
	public ArrayList<EnterpriseVO> product_info(String productname, String address, String etpph, String procont,
			String price);
	
	//예약테이블에 넣기
	public int insertOrder(String user, String pd_cd, String first, String last, int sum);

	//날짜계산식
	public int total_day(String first, String last);

	
	//리뷰작성
	public int reviewadd(String user_id, String code, String starcount, String content);

}
