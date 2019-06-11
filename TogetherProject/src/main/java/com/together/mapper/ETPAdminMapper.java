package com.together.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.EnterpriseVO;
import com.together.domain.OrdersVO;
import com.together.domain.ProductVO;

public interface ETPAdminMapper {

	// 업체 관리자 페이지 - 주문현황을 위한 함수 맵핑
	public ArrayList<EnterpriseVO> select_order_list(String code);

	public ArrayList<EnterpriseVO> textbox(@Param("sess") String sess);

	public ArrayList<OrdersVO> newInfo(String nm);

	public int updateChecked(@Param("day1") String day1, @Param("day2") String day2,
			@Param("check_val") String check_val, @Param("member_id") String member_id, @Param("day_th") String day_th);

	
	// 업체 관리자 페이지 - 상품 정보 : 테이블에 리스트를 보여주는 함수 맵핑
	public ArrayList<EnterpriseVO> info_select(String user_id);

	public ArrayList<ProductVO> product_select(@Param("code") String code);

	// 업체 관리자 페이지 - 상품 정보 -> Add창에서 상품을 추가할때 필요한 함수 맵핑
	public int insert_pro(@Param("code") String code, @Param("pd_nm") String pd_nm, @Param("pd_price") int pd_price,
			@Param("pd_content") String pd_content, @Param("pd_img_path") String pd_img_path);

	public ArrayList<ProductVO> st_insert_pro(@Param("code") String code, @Param("pd_nm") String pd_nm);

	public int stockint(@Param("total_code") String total_code, @Param("pro_code") String pro_code,
			@Param("pd_num") String pd_num);

	
	
	// 업체 관리자 페이지 - 상품 정보 : 상품삭제 함수 맵핑
	public int del(@Param("code") String code, @Param("nm") String nm);

	// 업체 관리자 페이지 - 업체 정보 수정 페이지
	public Integer update(@Param("etp_nm") String etp_nm, @Param("etp_addr") String etp_addr,
			@Param("etp_ph_no") String etp_ph_no, @Param("etp_email") String etp_email,
			@Param("etp_license_no") String etp_license_no, @Param("etp_cd") String etp_cd);

	public Integer update2(@Param("etp_if_info") String etp_if_info, @Param("etp_if_intro") String etp_if_intro,
			@Param("time1") String time1, @Param("time2") String time2, @Param("etp_cd") String etp_cd,
			@Param("etp_if_img_path") String etp_if_img_path);
	
	// 업체 상세 정보 인서트 함수 맵핑
	public int ent_info(String code);
	
	// 호텔, 장레, 병원 구분을 위한 맵핑
	public String etpKindCheck(@Param("user_id")String user_id);
	
}
