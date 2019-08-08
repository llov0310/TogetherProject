package com.together.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.DogsVO;
import com.together.domain.MemberVO;
import com.together.domain.OrdersVO;
import com.together.domain.PostVO;

public interface OldMypageMapper {

	public ArrayList<MemberVO> passCheak(@Param("user_id") String user_id);
	
	public ArrayList<MemberVO> memberinfo(@Param("user_id") String user_id);
	
	  public Integer passNew(@Param("user_id") String user_id, @Param("password")String password);


	public Integer infoNew(@Param("user_id") String user_id,@Param("phon") String phon);


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

	public Integer memberdel(@Param("user_id") String user_id, @Param("password")String password);

	public Integer delorder(@Param("or_cd") String or_cd, @Param("or_stat") String or_stat);
	
	
	//public Integer addFile(String da_uuid,String da_path,String da_name,String d_cd);
	
	
	//▲아마 이거 xml이랑 이름 같이해야한다했을걸
	
	public Integer addFile(@Param("da_uuid")String da_uuid,@Param("da_path") String da_path,@Param("da_name") String da_name,@Param("d_cd") String d_cd);
	
	public ArrayList<DogsVO> getD_cd(@Param("user_id")String user_id,@Param("d_nm") String d_nm,@Param("d_gender") int d_gender,@Param("d_kind") String d_kind,@Param("d_age") String d_age);

	public ArrayList<OrdersVO> searchdate(@Param("user_id")String user_id, @Param("day")int day);





}
