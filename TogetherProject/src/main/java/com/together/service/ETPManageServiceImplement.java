package com.together.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.together.domain.EnterpriseVO;
import com.together.domain.OrdersVO;
import com.together.domain.ProductVO;
import com.together.domain.StockVO;
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
		public ArrayList<EnterpriseVO> textbox(String sess) {
			// TODO Auto-generated method stub
			return mapper.textbox(sess);
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


		@Override
		public ArrayList<EnterpriseVO> etpcheck(String sess) {
			// TODO Auto-generated method stub
			return mapper.etpcheck(sess);
		}

		//각 업체 주문 리스트 조회
		@Override
		public ArrayList<EnterpriseVO> select_order_list(String code) {
			// TODO Auto-generated method stub
			
			return mapper.select_order_list(code);
		}


		//member 조회
		@Override
		public ArrayList<OrdersVO> newinfo(String nm) {
			// TODO Auto-generated method stub
			return mapper.newInfo(nm);
		}

		// 주문 상태 업데이트
		@Override
		public int updateChecked(String day1, String day2, String check_val, String member_id, String day_th) {
			// TODO Auto-generated method stub
			return mapper.updated(day1,day2,check_val,member_id,day_th);
		}

		//상품 재고를 위한 코드 조회
		@Override
		public ArrayList<ProductVO> st_insert_pro(String code, String pd_nm) {
			// TODO Auto-generated method stub
			return mapper.st_insert_pro(code,pd_nm);
		}

		//상품추가시 날짜 재고 코드생성
		@Override
		public int stockint(String total_code, String pro_code, String pd_num) {
			// TODO Auto-generated method stub
			return mapper.stockint(total_code,pro_code,pd_num);
		}

		//해당 상품 코드 조회
		@Override
		public ArrayList<ProductVO> checkPro(String code, String content, String p_nm) {
			// TODO Auto-generated method stub
			return mapper.checkpro(code,content,p_nm);
		}

		//일자별 재고 조회
		@Override
		public ArrayList<StockVO> StockCheckPro(String p_code, String sub_fir, String sub_las) {
			// TODO Auto-generated method stub
			return mapper.StockCheckPro(p_code,sub_fir,sub_las);
		}
}
