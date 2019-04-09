package com.together.service;

import java.util.ArrayList;


import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;

public interface CustomerService {
	public MemberVO login(String userid, String password);

	public int signup(MemberVO ins);

	public ArrayList<EnterpriseVO> list(String address_total);
}