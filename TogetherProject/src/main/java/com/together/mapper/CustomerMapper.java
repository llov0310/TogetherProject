package com.together.mapper;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;


import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.ProductVO;

public interface CustomerMapper {
	//로그인을 위한 함수 추가
	public MemberVO login(@Param("user_id") String user_id, @Param("password") String password);

	public int signup(MemberVO ins);

	public ArrayList<EnterpriseVO> list(String address_total);

	public ArrayList<EnterpriseVO> info(String test);

	public int ent_info(String code);

	public ArrayList<EnterpriseVO> getList(String code);

	public ArrayList<EnterpriseVO> getcd(@Param("code") String code, @Param("name") String name);

	public int insert_order(@Param("user") String user, 
			@Param("pdcode") String pdcode, 
			@Param("first") String first_day, 
			@Param("last") String last_day, 
			@Param("price") int price);

	public ArrayList<EnterpriseVO> address(@Param("address") String address, @Param("in") String in, @Param("out") String out);

	public ArrayList<EnterpriseVO> serchlist(@Param("serch") String serch, @Param("in") String in, @Param("out") String out);

	
	
	
}