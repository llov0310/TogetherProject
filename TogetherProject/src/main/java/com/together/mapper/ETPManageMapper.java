package com.together.mapper;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;


import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.OrdersVO;
import com.together.domain.ProductVO;
import com.together.domain.StockVO;
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
		
		public ArrayList<EnterpriseVO> textbox(@Param("sess") String sess);
		
		public ArrayList<EnterpriseVO> info_select(String user_id);
		
		public ArrayList<ProductVO> product_select(@Param ("code") String code);

		public int del(@Param("code") String code, @Param("nm") String nm);

		public int insert_product( @Param("code") String code, 
				@Param("pd_nm") String pd_nm, 
				@Param("pd_price") int pd_price, 
				@Param("pd_content") String pd_content);

		public ArrayList<EnterpriseVO> etpcheck(String sess);

		public ArrayList<EnterpriseVO> select_order_list(String code);

	
		public ArrayList<OrdersVO> newInfo(String nm);

		public int updated(@Param("day1") String day1,
				@Param("day2") String day2, 
				@Param("check_val") String check_val, 
				@Param("member_id") String member_id,
				@Param("day_th") String day_th
				);

		public ArrayList<ProductVO> st_insert_pro(@Param("code") String code, @Param("pd_nm") String pd_nm);

		public int stockint(@Param("total_code") String total_code,@Param("pro_code") String pro_code,@Param("pd_num")  String pd_num);

		public ArrayList<ProductVO> checkpro(@Param("code") String code, 
				@Param("content") String content, 
				@Param("p_nm") String p_nm);

		public ArrayList<StockVO> StockCheckPro(@Param("p_code") String p_code, 
				@Param("sub_fir") String sub_fir, 
				@Param("sub_las") String sub_las);
}
