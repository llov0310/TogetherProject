package com.together.service;

import java.util.ArrayList;

import com.together.domain.EnterpriseVO;
import com.together.domain.HospitalOrdersVO;
import com.together.domain.MemberVO;
import com.together.domain.ReviewBoardVO;

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

	//각업체별 리뷰 리스트 가져오기
	public ArrayList<ReviewBoardVO> reviewList(String etpcode);

	
	//리뷰 평점 조회
	public ArrayList<ReviewBoardVO> reviewcount(String etp_cd);

	//상품의 가격이 가장 낮은것의 가격
	public ArrayList<EnterpriseVO> firstproduct(String etp_cd);

	
	//장례업체 모두 가져오기
	public ArrayList<EnterpriseVO> funeralLIst(String formetData,String location);

	
	//장례 화장료 최소가
	public ArrayList<EnterpriseVO> firstfuneralproduct(String etp_cd);

	
	//장례업체 리뷰조회
	public ArrayList<ReviewBoardVO> funeral_review(String decodeResult);

	//장례업체 정보 조회
	public ArrayList<EnterpriseVO> funeralGetList(String decodeResult);

	
	//장례업체 상품 조회
	public ArrayList<EnterpriseVO> funeral1(String decodeResult);
	public ArrayList<EnterpriseVO> funeral2(String decodeResult);
	public ArrayList<EnterpriseVO> funeral3(String decodeResult);
	public ArrayList<EnterpriseVO> funeral4(String decodeResult);

	
	//장례상품 주문
	public int f_orders(String user_id, String pro_name, String price, String order_day, String code);

	//주문완료시 값을 보내주기위해 코드 셀렉트
	public ArrayList<EnterpriseVO> resultOrders(String order_day, String code, String user_id);

	
	//병원 카테고리 검색별로 나타나는 리스트
	public ArrayList<EnterpriseVO> getHospitalList(String day, String item1, String item2, String item3, String item4,
			String item5, String item6, String item7);

	//병원업체 최초로 정보 몇가지 가져오기
	public ArrayList<EnterpriseVO> detail_getHosList(String etp_nm);

	
	//각병원의 예약가능시간을 조회하기
	public ArrayList<HospitalOrdersVO> hospital_orders(String etp_cd, String s_time1, String s_time2);

	public int HosOrders(HospitalOrdersVO insVO);

	public ArrayList<HospitalOrdersVO> getHor_cd(HospitalOrdersVO insVO);

	public int HosDetail(String hor_cd, String canser);

}
