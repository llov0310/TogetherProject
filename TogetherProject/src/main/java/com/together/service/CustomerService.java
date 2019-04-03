package com.together.service;

import java.util.ArrayList;

import com.together.domain.EnterpriceVO;
import com.together.domain.MemberVO;

public interface CustomerService {
	public MemberVO login(String userid, String password);

	public int signup(MemberVO ins);

	public ArrayList<EnterpriceVO> list(String address_total);
}