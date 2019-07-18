package com.together.service;

import java.util.ArrayList;
import java.util.Map;

import com.together.domain.EnterpriseVO;
import com.together.domain.HospitalOrdersVO;

import com.together.domain.OrdersVO;
import com.together.domain.ProductVO;

public interface ETPAdminService {
	
	// 업체 관리자 페이지 - 주문현황을 위한 함수 추가
	public ArrayList<EnterpriseVO> textbox(String sess); // 업체 관리자 페이지 - 업체 정보 수정 페이지에도 사용됨
	public ArrayList<EnterpriseVO> select_order_list(String code);
	public ArrayList<OrdersVO> newinfo(String nm); // 팝업창에서 확인 버튼 클릭 시 쓰이는 함수 
	public int updateChecked(String day1, String day2, String check_val, String member_id,String day_th); // 팝업창에서 확인 버튼 클릭 시 쓰이는 함수 
	
	// 업체 관리자 페이지 - 상품 정보 : 테이블에 리스트를 보여주는 함수
	public ArrayList<EnterpriseVO> info_select(String user_id);
	public ArrayList<ProductVO> product_select(String code);
	
	// 업체 관리자 페이지 - 상품 정보 -> Add창에서 상품을 추가할때 필요한 함수
	public int insert_pro(String code, String pd_nm, int pd_price, String pd_content, String pd_img_path);
	public ArrayList<ProductVO> st_insert_pro(String code, String pd_nm);
	public int stockint(String total_code,String pro_code, String pd_num);
	
	// 업체 관리자 페이지 - 상품 정보 : 상품삭제 함수
	public int del(String code, String nm);

	// 업체 관리자 페이지 - 업체 정보 수정 페이지
	public Integer update(String etp_nm, String etp_addr, String etp_ph_no, String etp_license_no, String etp_email,String etp_cd);
	public Integer update2(String etp_if_info, String etp_if_intro, String time1, String time2, String etp_cd, String etp_if_img_path);
	
	// 업체 상세 정보 인서트 함수
	public int ent_info(String code);
	
	// 호텔, 장레, 병원 구분을 위한 메소드
	public String etpKindCheck(String user_id);
	
	// 업체 관리자 페이지(장례) : 상품 추가
	public int funeralProInsert(String code, String pd_nm, int pd_price, String pd_content, String pd_img_path, String ca_cd);

	// 업체 관리자 페이지(장례) : 주문 현황
	public ArrayList<EnterpriseVO> etpFuneralOrderList(String code);
	
	// 업체 관리자 페이지(장례) : bp팝업 창 -> 주문 상세 내역
	public ArrayList<OrdersVO> selectDetail(String or_dt, String or_dt2, String etp_cd);
	
	// 업체 관리자 페이지(장례) : 확인 update
	public int or_checkUpdate(String user_id, String or_dt, String or_dt2, String th_dt);
	
	//병원주문목록
	public ArrayList<HospitalOrdersVO> hospital_order_list(String user_id);
	//병원상세
	public ArrayList<HospitalOrdersVO> hospital_order_detail(String hor_cd);
	
	
}
