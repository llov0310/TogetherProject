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

	// 업체 신청 수락 함수
	@Override
	public int etpApplyManage_01(MemberVO mbIns, String arr) {
		// TODO Auto-generated method stub
		return mapper.etpApplyManage_01(mbIns, arr);
	}

	// 업체 신청 거절 함수
	@Override
	public int etpApplyManage_02(EnterpriseVO etpIns, String arr) {
		// TODO Auto-generated method stub
		return mapper.etpApplyManage_02(etpIns, arr);
	}
}