package com.together.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.MemberVO;

public interface MypageMapper {

	public ArrayList<MemberVO> passCheak(@Param("user_id") String user_id);

	

}
