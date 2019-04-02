package com.together.service;

import com.together.domain.MemberVO;

public interface CustomerService {
	public MemberVO login(String userid, String password);

	public int signup(MemberVO ins);
}