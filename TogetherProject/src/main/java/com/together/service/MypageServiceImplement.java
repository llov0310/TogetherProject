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


	@Override
	public ArrayList<DogsVO> petlist(String user_id) {
		// TODO Auto-generated method stub
		return myMapper.petlist(user_id);
	}


	@Override
	public ArrayList<MemberVO> memberinfo(String user_id) {
		// TODO Auto-generated method stub
		return myMapper.memberinfo(user_id);
	}


	@Override
	public Integer addDog(String user_id, String d_nm, int d_gender, String d_kind, String d_content, String d_age) {
		// TODO Auto-generated method stub
		return myMapper.addDog(user_id,d_nm,d_gender,d_kind,d_content, d_age);
	}


	@Override
	public Integer petdelete(String user_id, String d_nm) {
		// TODO Auto-generated method stub
		return myMapper.petdelete(user_id,d_nm);
	}

	 
}

