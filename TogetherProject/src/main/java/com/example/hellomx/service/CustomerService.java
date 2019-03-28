package com.example.hellomx.service;

import java.util.ArrayList;
import com.example.hellomx.domain.CustomerVO;
import com.example.hellomx.domain.MemberVO;

public interface CustomerService {
	public ArrayList<CustomerVO> getList();
	
//	public ArrayList<MemberVO> login(String id);
	
	public MemberVO login(String userid, String password);

	public int sign(MemberVO insert);
}