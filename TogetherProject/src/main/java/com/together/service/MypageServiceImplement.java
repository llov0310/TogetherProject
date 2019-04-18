package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.together.domain.MemberVO;

import com.together.mapper.MypageMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MypageServiceImplement implements MypageService {
	
	private MypageMapper myMapper;

	@Override
	public ArrayList<MemberVO> passCheak(String user_id) {
		// TODO Auto-generated method stub
		return myMapper.passCheak(user_id);
	}
	
}
