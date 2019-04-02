package com.together.mapper;

import java.util.ArrayList;

import com.together.domain.MemberVO;

public interface AdminMapper {
	// 회원 리스트를 가져오기 위한 함수 추가
	public ArrayList<MemberVO> getList();
}
