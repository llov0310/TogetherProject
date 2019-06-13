package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.together.domain.DogsVO;
import com.together.domain.MemberVO;
import com.together.domain.OrdersVO;
import com.together.domain.PostVO;
import com.together.mapper.OldMypageMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OldMypageServiceImplement implements OldMypageService {
	
	private OldMypageMapper myMapper;

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


	@Override
	public Integer petup(String user_id, String d_nm, int d_gender, String d_kind, String d_content, String d_age) {
		// TODO Auto-generated method stub
		return myMapper.petup(user_id,d_nm,d_gender,d_kind,d_content, d_age);
	}


	@Override
	public ArrayList<OrdersVO> orderlist(String user_id) {
		// TODO Auto-generated method stub
		return myMapper.orderlist(user_id);
	}


	@Override
	public ArrayList<PostVO> postlist(String user_id) {
		// TODO Auto-generated method stub
		return myMapper.postlist(user_id);
	}


	@Override
	public Integer memberdel(String user_id, String password) {
		// TODO Auto-generated method stub
		return myMapper.memberdel(user_id , password);
	}


	@Override
	public Integer delorder(String or_cd, String or_stat) {
		// TODO Auto-generated method stub
		return myMapper.delorder(or_cd,or_stat);
	}


	@Override
	public Integer addFile(String da_uuid, String da_path, String da_name, String d_cd) {
		return myMapper.addFile(da_uuid,da_path, da_name, d_cd);
	}


	@Override
	public ArrayList<DogsVO> getD_cd(String user_id, String d_nm, int d_gender, String d_kind, String d_age) {
		// TODO Auto-generated method stub
		return myMapper.getD_cd(user_id, d_nm, d_gender, d_kind, d_age);
	}


	@Override
	public ArrayList<OrdersVO> searchdate(String user_id, int day) {
		// TODO Auto-generated method stub
		return myMapper.searchdate(user_id,day);
	}

	 
}

