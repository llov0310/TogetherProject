package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.together.domain.EnterpriseVO;
import com.together.domain.ProductVO;
import com.together.mapper.CustomerMapper;
import com.together.mapper.ETPManageMapper;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ETPManageServiceImplement implements ETPManageService {
		private ETPManageMapper mapper;
		
		@Override
		public Integer update(String etp_nm, String etp_addr,
				String etp_ph_no, String etp_license_no, String etp_email,
				String etp_cd) {
			// TODO Auto-generated method stub
			return mapper.update(etp_nm,etp_addr,etp_ph_no,etp_email,etp_license_no,etp_cd);
		}


		@Override
		public Integer update2(String etp_if_info, String etp_if_intro, String time1, String time2, String etp_cd) {
			// TODO Auto-generated method stub
			return mapper.update2(etp_if_info,etp_if_intro,time1,time2,etp_cd);
		}
		
		@Override
		public ArrayList<ProductVO> product_select(String code) {
			// TODO Auto-generated method stub
			return mapper.product_select(code);
		}


		@Override
		public int del(String code, String nm) {
			// TODO Auto-generated method stub
			return mapper.del(code,nm);
		}


		@Override
		public int insert_pro(String code, String pd_nm, int pd_price, String pd_content) {
			// TODO Auto-generated method stub
			return mapper.insert_product(code,pd_nm,pd_price,pd_content);
		}
		
		@Override
		public  ArrayList<EnterpriseVO> info_select(String user_id) {
			// TODO Auto-generated method stub
			return mapper.info_select(user_id);
		}
}
