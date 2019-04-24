package com.together.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.MemberVO;

public interface MypageMapper {

	public ArrayList<MemberVO> passCheak(@Param("user_id") String user_id);

	
	  public Integer passNew(@Param("user_id") String user_id, @Param("password")String password);


	public Integer infoNew(@Param("user_id") String user_id,
			@Param("email") String email, 
			@Param("addr_ji") String addr_ji, 
			@Param("addr_dong") String addr_dong, 
			@Param("phon") String phon
			);


	
	

}
