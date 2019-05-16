package com.together.mapper;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;


import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.ProductVO;
public interface ETPManageMapper {
	//업체 정보 수정
		public Integer update(@Param("etp_nm") String etp_nm, 
				@Param("etp_addr") String etp_addr, 
				@Param("etp_ph_no") String etp_ph_no, 
				@Param("etp_email") String etp_email, 
				@Param("etp_license_no") String etp_license_no,
				@Param("etp_cd") String etp_cd);

		public Integer update2(@Param("etp_if_info") String etp_if_info, 
				@Param("etp_if_intro") String etp_if_intro, 
				@Param("time1") String time1, 
				@Param("time2") String time2, 
				@Param("etp_cd")String etp_cd);
		
		
		public ArrayList<EnterpriseVO> info_select(String user_id);
		
		public ArrayList<ProductVO> product_select(@Param ("code") String code);

		public int del(@Param("code") String code, @Param("nm") String nm);

		public int insert_product( @Param("code") String code, 
				@Param("pd_nm") String pd_nm, 
				@Param("pd_price") int pd_price, 
				@Param("pd_content") String pd_content);
}
