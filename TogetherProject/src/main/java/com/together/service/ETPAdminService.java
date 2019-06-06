package com.together.service;

import java.util.ArrayList;

import com.together.domain.EnterpriseVO;
import com.together.domain.ProductVO;

public interface ETPAdminService {
	
	// 업체 관리자 페이지 - 주문현황을 위한 함수 추가
	public ArrayList<EnterpriseVO> textbox(String sess);
	public ArrayList<EnterpriseVO> select_order_list(String code);
	
	// 업체 관리자 페이지 - 상품 정보 : 테이블에 리스트를 보여주는 함수
	public  ArrayList<EnterpriseVO> info_select(String user_id);
	public ArrayList<ProductVO> product_select(String code);
	
	// 업체 관리자 페이지 - 상품 정보 -> Add창에서 상품을 추가할때 필요한 함수
	public int insert_pro(String code, String pd_nm, int pd_price, String pd_content, String insert_pro);
	public ArrayList<ProductVO> st_insert_pro(String code, String pd_nm);
	public int stockint(String total_code,String pro_code, String pd_num);
	
	// 업체 관리자 페이지 - 상품 정보 : 상품삭제 함수
	public int del(String code, String nm);

}
