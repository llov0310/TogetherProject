package com.together.mapper;

import com.together.domain.EnterpriseVO;

public interface ETPApplyMapper {
	// 업체 신청 함수 맵핑
	public int etpApply(EnterpriseVO ins);
	
	public int etpApply2(EnterpriseVO ins);
}