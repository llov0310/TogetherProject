package com.together.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.ReviewBoardVO;

public interface AppServiceMapper {

	public ArrayList<EnterpriseVO> hotelList(@Param("in") String in, @Param("out") String out, @Param("address") String Address);

	public ArrayList<EnterpriseVO> hotelinfoList(@Param("name") String etpName, @Param("addr") String etpAddr);

	public ArrayList<EnterpriseVO> ProductList(@Param("code") String etpcode, @Param("in") String etpfirst, @Param("out") String etplast);

	public ArrayList<MemberVO> user_info(String user);

	public ArrayList<EnterpriseVO> product_info(@Param("proname") String productname, 
			@Param("addr") String address, @Param("ph") String etpph, 
			@Param("cont")String procont, @Param("price") String price);

	public int insertOrder(@Param("user") String user, @Param("code") String pd_cd, 
			@Param("in") String first, @Param("out") String last, 
			@Param("price") int sum);

	public int total_day(@Param("in") String first, @Param("out") String last);

	public int reviewadd(@Param("id") String user_id, 
			@Param("code") String code, 
			@Param("star") String starcount, 
			@Param("content") String content);

	public ArrayList<ReviewBoardVO> reviewList(String etpcode); 

	

}
