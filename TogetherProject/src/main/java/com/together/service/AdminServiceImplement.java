package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.together.domain.DogsVO;
import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.mapper.AdminMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImplement implements AdminService {
	private AdminMapper mapper;

	// 회원 리스트 가져오는 함수
	@Override
	public ArrayList<MemberVO> getMemberList() {
		return mapper.getMemberList();
	}

	// 업체 리스트 가져오는 함수
	@Override
	public ArrayList<EnterpriseVO> getEnterpriseList() {
		return mapper.getEnterpriseList();
	}
	
	// 반려견 리스트 가져오는 함수
	@Override
	public ArrayList<DogsVO> getDogsList() {
		// TODO Auto-generated method stub
		return mapper.getDogsList();
	}
	
	//업체 신청 수락
	@Override
	public int etpApplyManage_01(String user_id) {
		// TODO Auto-generated method stub
		return mapper.etpApplyManage_01(user_id);
	}

	//업체 신청 거절
	@Override
	public int etpApplyManage_02(String user_id) {
		// TODO Auto-generated method stub
		return mapper.etpApplyManage_02(user_id);
	}


}