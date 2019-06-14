package com.together.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.MemberVO;

public interface MyPageMapper {
	// 회원 정보 수정 전 패스워드 체크 맵핑
	public ArrayList<MemberVO> passWordCk(@Param("user_id") String user_id, @Param("password") String password);

	// 프로필 이미지, 주소, 이메일, 휴대폰 번호 변경(업데이트) 맵핑
	public int memberUpdate(@Param("user_id") String user_id, @Param("userImg_path") String userImg_path);
}
