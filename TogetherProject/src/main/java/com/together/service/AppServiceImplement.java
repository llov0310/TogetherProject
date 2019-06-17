package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.ReviewBoardVO;
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

	
	@Override
	public ArrayList<ReviewBoardVO> reviewList(String etpcode) {
		// TODO Auto-generated method stub
		return mapper.reviewList(etpcode);
	}

	@Override
	public ArrayList<ReviewBoardVO> reviewcount(String etp_cd) {
		// TODO Auto-generated method stub
		return mapper.reviewcount(etp_cd);
	}

	@Override
	public ArrayList<EnterpriseVO> firstproduct(String etp_cd) {
		// TODO Auto-generated method stub
		return mapper.firstproduct(etp_cd);
	}

	@Override
	public ArrayList<EnterpriseVO> funeralLIst(String formetData,String location) {
		// TODO Auto-generated method stub
		return mapper.funeralList(formetData,location);
	}

	@Override
	public ArrayList<EnterpriseVO> firstfuneralproduct(String etp_cd) {
		// TODO Auto-generated method stub
		return mapper.firstfuneralproduct(etp_cd);
	}

	@Override
	public ArrayList<ReviewBoardVO> funeral_review(String decodeResult) {
		// TODO Auto-generated method stub
		return mapper.funeral_review(decodeResult);
	}

	@Override
	public ArrayList<EnterpriseVO> funeralGetList(String decodeResult) {
		// TODO Auto-generated method stub
		return mapper.funeralgetList(decodeResult);
	}

	
	//격리 공간 (장례상품조회)
	@Override
	public ArrayList<EnterpriseVO> funeral1(String decodeResult) {
		// TODO Auto-generated method stub
		return mapper.funeral1(decodeResult);
	}

	@Override
	public ArrayList<EnterpriseVO> funeral2(String decodeResult) {
		// TODO Auto-generated method stub
		return mapper.funeral2(decodeResult);
	}

	@Override
	public ArrayList<EnterpriseVO> funeral3(String decodeResult) {
		// TODO Auto-generated method stub
		return mapper.funeral3(decodeResult);
	}

	@Override
	public ArrayList<EnterpriseVO> funeral4(String decodeResult) {
		// TODO Auto-generated method stub
		return mapper.funeral4(decodeResult);
	}

}
