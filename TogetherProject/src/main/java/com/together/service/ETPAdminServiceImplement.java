package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.together.domain.EnterpriseVO;
import com.together.domain.OrdersVO;
import com.together.domain.ProductVO;
import com.together.mapper.ETPAdminMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ETPAdminServiceImplement implements ETPAdminService {
	ETPAdminMapper mapper;

	// 업체 관리자 페이지 - 주문현황을 위한 함수 추가
	@Override
	public ArrayList<EnterpriseVO> textbox(String sess) {
		// TODO Auto-generated method stub
		return mapper.textbox(sess);
	}

	@Override
	public ArrayList<EnterpriseVO> select_order_list(String code) {
		// TODO Auto-generated method stub
		return mapper.select_order_list(code);
	}

	@Override
	public ArrayList<OrdersVO> newinfo(String nm) {
		// TODO Auto-generated method stub
		return mapper.newInfo(nm);
	}

	@Override
	public int updateChecked(String day1, String day2, String check_val, String member_id, String day_th) {
		// TODO Auto-generated method stub
		return mapper.updateChecked(day1, day2, check_val, member_id, day_th);
	}
	
	
	// 업체 관리자 페이지 - 상품 정보 : 테이블에 리스트를 보여주는 함수
	@Override
	public ArrayList<EnterpriseVO> info_select(String user_id) {
		// TODO Auto-generated method stub
		return mapper.info_select(user_id);
	}

	@Override
	public ArrayList<ProductVO> product_select(String code) {
		// TODO Auto-generated method stub
		return mapper.product_select(code);
	}

	// 업체 관리자 페이지 - 상품 정보 -> Add창에서 상품을 추가할때 필요한 함수
	@Override
	public int insert_pro(String code, String pd_nm, int pd_price, String pd_content, String pd_img_path) {
		// TODO Auto-generated method stub
		return mapper.insert_pro(code, pd_nm, pd_price, pd_content, pd_img_path);
	}

	@Override
	public ArrayList<ProductVO> st_insert_pro(String code, String pd_nm) {
		// TODO Auto-generated method stub
		return mapper.st_insert_pro(code, pd_nm);
	}

	@Override
	public int stockint(String total_code, String pro_code, String pd_num) {
		// TODO Auto-generated method stub
		return mapper.stockint(total_code, pro_code, pd_num);
	}
	
	// 업체 관리자 페이지 - 상품 정보 : 상품삭제 함수
	@Override
	public int del(String code, String nm) {
		// TODO Auto-generated method stub
		return mapper.del(code, nm);
	}
	
	// 업체 관리자 페이지 - 업체 정보 수정 페이지
	@Override
	public Integer update(String etp_nm, String etp_addr, String etp_ph_no, String etp_license_no, String etp_email,
			String etp_cd) {
		// TODO Auto-generated method stub
		return mapper.update(etp_nm, etp_addr, etp_ph_no, etp_email, etp_license_no, etp_cd);
	}

	@Override
	public Integer update2(String etp_if_info, String etp_if_intro, String time1, String time2, String etp_cd,
			String etp_if_img_path) {
		// TODO Auto-generated method stub
		return mapper.update2(etp_if_info, etp_if_intro, time1, time2, etp_cd, etp_if_img_path);
	}
	
	// 업체 상세 정보 인서트 함수
	@Override
	public int ent_info(String code) {
		// TODO Auto-generated method stub
		return mapper.ent_info(code);
	}
	
	// 호텔, 장레, 병원 구분을 위한 메소드
	@Override
	public String etpKindCheck(String user_id) {
		// TODO Auto-generated method stub
		return mapper.etpKindCheck(user_id);
	}

	// 업체 관리자 페이지(장례) : 상품 추가
	@Override
	public int funeralProInsert(String code, String pd_nm, int pd_price, String pd_content, String pd_img_path,
			String ca_cd) {
		// TODO Auto-generated method stub
		return mapper.funeralProInsert(code, pd_nm, pd_price, pd_content, pd_img_path, ca_cd);
	}
	//
	
	
}
