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
	public ArrayList<EnterpriseVO> ser(String toAddress) {
		// TODO Auto-generated method stub
		return mapper.ser(toAddress);
	}


	@Override
	public ArrayList<EnterpriseVO> info(String test) {
		// TODO Auto-generated method stub
		return mapper.info(test);
	}


	@Override
	public ArrayList<EnterpriseVO> textbox(String sess) {
		// TODO Auto-generated method stub
		return mapper.textbox(sess);
	}


	@Override
	public Integer update(String etp_nm, String etp_addr,
			String etp_ph_no, String etp_license_no, String etp_email,
			String etp_cd) {
		// TODO Auto-generated method stub
		return mapper.update(etp_nm,etp_addr,etp_ph_no,etp_email,etp_license_no,etp_cd);
	}


	@Override
	public Integer update2(String etp_if_info, String etp_if_intro, String time1, String time2, String etp_cd) {
		// TODO Auto-generated method stub
		return mapper.update2(etp_if_info,etp_if_intro,time1,time2,etp_cd);
	}


	@Override
	public  ArrayList<EnterpriseVO> info_select(String user_id) {
		// TODO Auto-generated method stub
		return mapper.info_select(user_id);
	}


	@Override
	public int ent_info(String code) {
		// TODO Auto-generated method stub
		return mapper.ent_info(code);
	}


	@Override
	public ArrayList<ProductVO> product_select(String code) {
		// TODO Auto-generated method stub
		return mapper.product_select(code);
	}


	@Override
	public int del(String code, String nm) {
		// TODO Auto-generated method stub
		return mapper.del(code,nm);
	}


	@Override
	public int insert_pro(String code, String pd_nm, int pd_price, String pd_content) {
		// TODO Auto-generated method stub
		return mapper.insert_product(code,pd_nm,pd_price,pd_content);
	}


	



}