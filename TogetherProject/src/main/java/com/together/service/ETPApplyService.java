package com.together.service;

import java.util.ArrayList;

import com.together.domain.EnterpriseVO;

public interface ETPApplyService {
	// 업체 신청을 위한 메소드 추가
	public int etpApply(EnterpriseVO ins);

	public int etpApply2(EnterpriseVO ins);
	
	// 업체 상세 정보 리스트
	public  ArrayList<EnterpriseVO> info_select(String user_id);
	
	// 업체 상세 정보 인서트 함수
	public int ent_info(String code);
}
