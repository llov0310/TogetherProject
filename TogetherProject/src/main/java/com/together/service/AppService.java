package com.together.service;

import java.util.ArrayList;

import com.together.domain.EnterpriseVO;

public interface AppService {

	public ArrayList<EnterpriseVO> hotelList(String in, String out, String address);

}
