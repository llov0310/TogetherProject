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

	public ArrayList<EnterpriseVO> ser(String toAddress);

	public ArrayList<EnterpriseVO> info(String test);

	public ArrayList<EnterpriseVO> textbox(@Param("sess") String sess);

	public int etpupdate(String etp_nm, String etp_if_info, String etp_if_intro, String etp_addr, String etp_ph_no,
			String etp_license_no, String etp_email, String time1, String time2);
	
	
}