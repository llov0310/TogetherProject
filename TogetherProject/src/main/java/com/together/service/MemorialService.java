package com.together.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.together.domain.DogsVO;
import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.SadBoardVO;

public interface MemorialService {
	
	 // 회원정보를 얻어올 함수 선언
	   public ArrayList<MemberVO> getMemberList();

	   // 업체 리스트를 가져올 함수 선언
	   public ArrayList<EnterpriseVO> getEnterpriseList();
	   
	   // 반려견 리스트를 가져올 함수 선언
	   public ArrayList<DogsVO> getDogsList();
	
	
	
}
