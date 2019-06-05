package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.together.domain.EnterpriseVO;
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

}
