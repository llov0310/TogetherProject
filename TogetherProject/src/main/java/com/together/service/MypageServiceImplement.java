package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.together.domain.DogsVO;
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

	
	  @Override 
	  public Integer passNew(String user_id, String password) { // TODO
	 return myMapper.passNew(user_id,password); 
	 }


	@Override
	public Integer infoNew(String user_id ,String email, String addr_ji, String addr_dong, String phon) {
		// TODO Auto-generated method stub
		return myMapper.infoNew(user_id,email ,addr_ji,addr_dong, phon);
	}


	 
}

