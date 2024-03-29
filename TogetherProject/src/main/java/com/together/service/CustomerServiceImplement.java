package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;


import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.ProductVO;
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
	public ArrayList<EnterpriseVO> info(String test) {
		// TODO Auto-generated method stub
		return mapper.info(test);
	}

	@Override
	public int ent_info(String code) {
		// TODO Auto-generated method stub
		return mapper.ent_info(code);
	}

	//상품목록조회
	@Override
	public ArrayList<EnterpriseVO> getList(String code) {
		// TODO Auto-generated method stub
		return mapper.getList(code);
	}

	//예약을 하기위한 조회
	@Override
	public ArrayList<EnterpriseVO> info_list(String code,String name) {
		// TODO Auto-generated method stub
		return mapper.getcd(code,name);
	}


	@Override
	public int insert_order(String user, String pdcode, String first_day, String last_day, int price) {
		// TODO Auto-generated method stub
		return mapper.insert_order(user,pdcode,first_day,last_day,price);
	}

	//주소와 기간으로 검색
	@Override
	public ArrayList<EnterpriseVO> ser(String Address, String in, String out) {
		// TODO Auto-generated method stub
		return mapper.address(Address,in,out);
	}

	//주소와 검색어로 검색
	@Override
	public ArrayList<EnterpriseVO> ser2(String serch, String in, String out) {
		// TODO Auto-generated method stub
		return mapper.serchlist(serch,in,out);
	}

	//Hotel 예약시 재고 차감
	@Override
	public int update_order(String first_day, String last_day, String pdcode) {
		// TODO Auto-generated method stub
		return mapper.update_order(first_day, last_day, pdcode);
	}


	@Override
	public ArrayList<MemberVO> loginCheck(String user_id, String password) {
		// TODO Auto-generated method stub
		return mapper.loginCheck(user_id, password);
	}


	



}