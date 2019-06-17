package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.together.domain.MemberVO;
import com.together.mapper.MyPageMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MyPageServiceImplement implements MyPageService {
	private MyPageMapper mapper;
	
	// 회원정보 변경 전 비밀번호체크
	@Override
	public ArrayList<MemberVO> passWordCk(String user_id, String password) { 
		// TODO Auto-generated method stub
		return mapper.passWordCk(user_id, password);
	}
	
	//  프로필 이미지, 주소, 이메일, 휴대폰 번호 변경
	@Override
	public int memberUpdate(String user_id, String userImg_path) {
		// TODO Auto-generated method stub
		return mapper.memberUpdate(user_id, userImg_path);
	}

}
