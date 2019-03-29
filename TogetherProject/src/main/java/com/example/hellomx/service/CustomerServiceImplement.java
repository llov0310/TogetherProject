package com.example.hellomx.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.example.hellomx.domain.MemberVO;
import com.example.hellomx.mapper.CustomerMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImplement implements CustomerService {
	private CustomerMapper mapper;

	//로그인
	@Override
	public MemberVO login(String userid, String password) {
		// TODO Auto-generated method stub
		return mapper.login(userid, password);
	}

@Override
	public int signup(MemberVO ins) {
		// TODO Auto-generated method stub
		return mapper.signup(ins);
	}



}