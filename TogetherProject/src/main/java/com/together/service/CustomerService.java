package com.together.service;

import java.util.ArrayList;


import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.ProductVO;

public interface CustomerService {
	public MemberVO login(String user_id, String password);

	public int signup(MemberVO ins);

	public ArrayList<EnterpriseVO> list(String address_total);
	
	public ArrayList<EnterpriseVO> info(String test);

	public int ent_info(String code);

	public ArrayList<EnterpriseVO> getList(String code); //상품목록 조회

	public ArrayList<EnterpriseVO> info_list(String code, String name);

	public int insert_order(String user, String pdcode, String first_day, String last_day, int price);

	public ArrayList<EnterpriseVO> ser(String Address,String in,String out);

	public ArrayList<EnterpriseVO> ser2(String serch, String in, String out);

	public int update_order(String first_day, String last_day, String pdcode); //예약시 재고 차감

	
	
}