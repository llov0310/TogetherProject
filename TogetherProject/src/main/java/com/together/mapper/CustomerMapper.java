package com.together.mapper;


import org.apache.ibatis.annotations.Param;

import com.together.domain.MemberVO;

public interface CustomerMapper {
	//로그인을 위한 함수 추가
	public MemberVO login(@Param("userid") String userid, @Param("password") String password);

	public int signup(MemberVO ins);
}