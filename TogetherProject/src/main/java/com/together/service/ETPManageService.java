package com.together.service;
import java.util.ArrayList;


import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.ProductVO;
public interface ETPManageService {
	public Integer update(String etp_nm, String etp_addr, String etp_ph_no, String etp_license_no, String etp_email,
			String etp_cd);

	public Integer update2(String etp_if_info, String etp_if_intro, String time1, String time2, String etp_cd);
	
	public  ArrayList<EnterpriseVO> info_select(String user_id);
	
	public ArrayList<ProductVO> product_select(String code);

	public int del(String code, String nm);

	public int insert_pro(String code, String pd_nm, int pd_price, String pd_content);
}
