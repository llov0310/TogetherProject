package com.together.mapper;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;


import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;

public interface CustomerMapper {
	//로그인을 위한 함수 추가
	public MemberVO login(@Param("user_id") String user_id, @Param("password") String password);

	public int signup(MemberVO ins);

	public ArrayList<EnterpriseVO> list(String address_total);
}