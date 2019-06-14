package com.together.service;

import java.util.ArrayList;

import com.together.domain.MemberVO;

public interface MyPageService {
	// 회원정보 변경 전 비밀번호체크
	public ArrayList<MemberVO> passWordCk(String user_id, String password);
	
	//  프로필 이미지, 주소, 이메일, 휴대폰 번호 변경
	public int memberUpdate(String user_id, String userImg_path);

}
