package com.together.service;
import java.util.ArrayList;


import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.OrdersVO;
import com.together.domain.ProductVO;
import com.together.domain.StockVO;
public interface ETPManageService {
	public Integer update(String etp_nm, String etp_addr, String etp_ph_no, String etp_license_no, String etp_email,
			String etp_cd);

	public Integer update2(String etp_if_info, String etp_if_intro, String time1, String time2, String etp_cd);
	
	public  ArrayList<EnterpriseVO> info_select(String user_id);
	
	public ArrayList<ProductVO> product_select(String code);

	public int del(String code, String nm);

	public int insert_pro(String code, String pd_nm, int pd_price, String pd_content);
	
	public ArrayList<EnterpriseVO> textbox(String sess);

	public ArrayList<EnterpriseVO> etpcheck(String sess);

	public ArrayList<EnterpriseVO> select_order_list(String code);

	public ArrayList<OrdersVO> newinfo(String nm);

	public int updateChecked(String day1, String day2, String check_val, String member_id,String day_th);

	public ArrayList<ProductVO> st_insert_pro(String code, String pd_nm);

	public int stockint(String total_code,String pro_code, String pd_num);

	public ArrayList<ProductVO> checkPro(String code, String content, String p_nm);

	public ArrayList<StockVO> StockCheckPro(String p_code, String sub_fir, String sub_las);
}
