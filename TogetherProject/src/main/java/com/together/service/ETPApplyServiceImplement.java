package com.together.service;

import org.springframework.stereotype.Service;

import com.together.domain.EnterpriseVO;
import com.together.mapper.ETPApplyMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ETPApplyServiceImplement implements ETPApplyService{
	
	private ETPApplyMapper mapper;
	
	
	// 업체 신청 (호텔)
	@Override
	public int etpApply(EnterpriseVO ins) {

		return mapper.etpApply(ins);
	}

	// 업체 신청 (장례)
	@Override
	public int etpApply2(EnterpriseVO ins) {
		// TODO Auto-generated method stub
		return mapper.etpApply2(ins);
	}
	
}
