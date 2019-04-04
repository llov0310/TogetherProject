package com.together.service;


import java.util.ArrayList;

import com.together.domain.MemberVO;

public interface AdminService {
	
   // 회원정보를 얻어올 함수 선언
   public ArrayList<MemberVO> getMemberList();
}