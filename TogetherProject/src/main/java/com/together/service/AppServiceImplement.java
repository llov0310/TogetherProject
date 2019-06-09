package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.mapper.AppServiceMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppServiceImplement implements AppService {
	
	private AppServiceMapper mapper;
	
	@Override
	public ArrayList<EnterpriseVO> hotelList(String in, String out, String Address) {
		// TODO Auto-generated method stub
		return mapper.hotelList(in,out,Address);
	}

	@Override
	public ArrayList<EnterpriseVO> ProductList(String etpName, String etpAddr) {
		// TODO Auto-generated method stub
		return mapper.hotelinfoList(etpName,etpAddr);
	}

	@Override
	public ArrayList<EnterpriseVO> StockList(String etpcode, String etpfirst, String etplast) {
		// TODO Auto-generated method stub
		return mapper.ProductList(etpcode, etpfirst, etplast);
	}

	
	@Override
	public ArrayList<MemberVO> user_info(String user) {
		// TODO Auto-generated method stub
		return mapper.user_info(user);
	}

	@Override
	public ArrayList<EnterpriseVO> product_info(String productname, String address, String etpph, String procont,
			String price) {
		// TODO Auto-generated method stub
		return mapper.product_info(productname,address,etpph,procont,price);
	}

	@Override
	public int insertOrder(String user, String pd_cd, String first, String last, int sum) {
		// TODO Auto-generated method stub
		return mapper.insertOrder(user,pd_cd,first,last,sum);
	}

	@Override
	public int total_day(String first, String last) {
		// TODO Auto-generated method stub
		return mapper.total_day(first,last);
	}

	@Override
	public int reviewadd(String user_id, String code, String starcount, String content) {
		// TODO Auto-generated method stub
		return mapper.reviewadd(user_id,code,starcount,content);
	}

}
