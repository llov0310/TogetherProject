package com.together.service;

import java.util.ArrayList;

import com.together.domain.MemberVO;

public interface MyPageService {
	//비밀번호체크
	public ArrayList<MemberVO> passWordCk(String user_id, String password);
}
