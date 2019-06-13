package com.together.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.MemberVO;

public interface MyPageMapper {
	
	public ArrayList<MemberVO> passWordCk(@Param("user_id") String user_id, @Param("password") String password);
}
