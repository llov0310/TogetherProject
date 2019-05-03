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

	
	//업체 정보 수정
	public Integer update(@Param("etp_nm") String etp_nm, 
			@Param("etp_addr") String etp_addr, 
			@Param("etp_ph_no") String etp_ph_no, 
			@Param("etp_email") String etp_email, 
			@Param("etp_license_no") String etp_license_no,
			@Param("etp_cd") String etp_cd);

	public Integer update2(@Param("etp_if_info") String etp_if_info, 
			@Param("etp_if_intro") String etp_if_intro, 
			@Param("time1") String time1, 
			@Param("time2") String time2, 
			@Param("etp_cd")String etp_cd);

	public  ArrayList<EnterpriseVO> info_select(String user_id);

	public int ent_info(String code);

	
	
	
}