package com.together.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.DogsVO;
import com.together.domain.MemberVO;
import com.together.domain.OrdersVO;
import com.together.domain.PostVO;

public interface MypageMapper {

	public ArrayList<MemberVO> passCheak(@Param("user_id") String user_id);
	
	public ArrayList<MemberVO> memberinfo(@Param("user_id") String user_id);
	
	  public Integer passNew(@Param("user_id") String user_id, @Param("password")String password);


	public Integer infoNew(@Param("user_id") String user_id,
			@Param("email") String email, 
			@Param("addr_ji") String addr_ji, 
			@Param("addr_dong") String addr_dong, 
			@Param("phon") String phon
			);


	public ArrayList<DogsVO> petlist(@Param("user_id") String user_id);

	public Integer addDog(@Param("user_id")String user_id,
						  @Param("d_nm") String d_nm,
						  @Param("d_gender")int d_gender,
						  @Param("d_kind")String d_kind,
						  @Param("d_content") String d_content,
						  @Param("d_age")String d_age);

	public Integer petdelete(@Param("user_id") String user_id, @Param("d_nm") String d_nm);

	public Integer petup(@Param("user_id")String user_id,
						  @Param("d_nm") String d_nm,
						  @Param("d_gender")int d_gender,
						  @Param("d_kind")String d_kind,
						  @Param("d_content") String d_content,
						  @Param("d_age")String d_age);

	public ArrayList<OrdersVO> orderlist(@Param("user_id") String user_id);

	public ArrayList<PostVO> postlist(@Param("user_id") String user_id);





}
