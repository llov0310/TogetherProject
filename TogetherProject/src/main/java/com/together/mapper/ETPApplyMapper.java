package com.together.mapper;

import org.apache.ibatis.annotations.Param;

import com.together.domain.EnterpriseVO;

public interface ETPApplyMapper {
	// 업체 신청 함수 맵핑
	public int etpApply(EnterpriseVO ins);
}