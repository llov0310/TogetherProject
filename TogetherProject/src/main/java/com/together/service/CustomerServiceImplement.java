package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;


import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.mapper.CustomerMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImplement implements CustomerService {
	private CustomerMapper mapper;

	//로그인
	@Override
	public MemberVO login(String user_id, String password) {
		// TODO Auto-generated method stub
		return mapper.login(user_id, password);
	}
	
	
	//회원가입
	@Override
	public int signup(MemberVO ins) {
		// TODO Auto-generated method stub
		return mapper.signup(ins);
	}

 
	@Override
	public ArrayList<EnterpriseVO> list(String address_total) {
		// TODO Auto-generated method stub
		return mapper.list(address_total);
	}


	@Override
	public ArrayList<EnterpriseVO> ser(String toAddress) {
		// TODO Auto-generated method stub
		return mapper.ser(toAddress);
	}



}