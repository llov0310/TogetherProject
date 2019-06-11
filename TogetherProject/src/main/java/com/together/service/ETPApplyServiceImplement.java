package com.together.service;

import java.util.ArrayList;

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
	
	// 업체 신청 (병원)
	@Override
	public int etpApply3(EnterpriseVO ins) {
		// TODO Auto-generated method stub
		return mapper.etpApply3(ins);
	}
	
	
	@Override
	public ArrayList<EnterpriseVO> info_select(String user_id) {
		// TODO Auto-generated method stub
		return mapper.info_select(user_id);
	}

	@Override
	public int ent_info(String code) {
		// TODO Auto-generated method stub
		return mapper.ent_info(code);
	}

	
}
