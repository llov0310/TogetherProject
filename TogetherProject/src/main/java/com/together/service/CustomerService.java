package com.together.service;

import java.util.ArrayList;


import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;

public interface CustomerService {
	public MemberVO login(String user_id, String password);

	public int signup(MemberVO ins);

	public ArrayList<EnterpriseVO> list(String address_total);

	public ArrayList<EnterpriseVO> ser(String toAddress);

	public ArrayList<EnterpriseVO> info(String test);

	public ArrayList<EnterpriseVO> textbox(String sess);
}