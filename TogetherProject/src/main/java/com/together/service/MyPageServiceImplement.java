package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.together.domain.MemberVO;
import com.together.mapper.MyPageMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MyPageServiceImplement implements MyPageService {
	private MyPageMapper mapper;
	
	@Override
	public ArrayList<MemberVO> passWordCk(String user_id, String password) {
		// TODO Auto-generated method stub
		return mapper.passWordCk(user_id, password);
	}

}
