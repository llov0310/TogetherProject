package com.together.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.EnterpriseVO;

public interface AppServiceMapper {

	public ArrayList<EnterpriseVO> hotelList(@Param("in") String in, @Param("out") String out, @Param("address") String Address);

}
