package com.together.mapper;

import java.util.ArrayList;

import com.together.domain.EnterpriseVO;
import com.together.domain.EnterpriseattachVO;
import com.together.domain.HospitalcategoryVO;

public interface ETPApplyMapper {
	// 업체 신청 함수 맵핑
	public int etpApply(EnterpriseVO ins); // 호텔
	
	public int etpApply2(EnterpriseVO ins); // 장례
	
	public int etpApply3(EnterpriseVO ins); // 병원
	
	// 업체 관리자 페이지 - 상품 정보 : 테이블에 리스트를 보여주는 함수 맵핑
	public ArrayList<EnterpriseVO> info_select(String user_id);
	
	// 업체 상세 정보 인서트 함수 맵핑
	public int ent_info(String code);

	public int ent_category(HospitalcategoryVO hospitalcategoryVO);

	public int ent_attach(EnterpriseattachVO enterpriseattachVO);
}