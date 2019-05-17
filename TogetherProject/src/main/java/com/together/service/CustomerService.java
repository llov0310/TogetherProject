package com.together.service;

import java.util.ArrayList;


import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.ProductVO;

public interface CustomerService {
	public MemberVO login(String user_id, String password);

	public int signup(MemberVO ins);

	public ArrayList<EnterpriseVO> list(String address_total);

	public ArrayList<EnterpriseVO> ser(String toAddress);

	public ArrayList<EnterpriseVO> info(String test);

	public int ent_info(String code);

	public ArrayList<EnterpriseVO> getList(String code); //상품목록 조회

	public ArrayList<EnterpriseVO> info_list(String code, String name);

	public int insert_order(String user, String pdcode, String first_day, String last_day, int price);

	
	
}