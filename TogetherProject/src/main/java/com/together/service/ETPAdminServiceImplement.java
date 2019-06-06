package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.together.domain.EnterpriseVO;
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
	public int insert_pro(String code, String pd_nm, int pd_price, String pd_content, String insert_pro) {
		// TODO Auto-generated method stub
		return mapper.insert_pro(code, pd_nm, pd_price, pd_content, insert_pro);
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

	//
	
	
}
