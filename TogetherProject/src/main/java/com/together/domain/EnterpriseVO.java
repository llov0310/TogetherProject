package com.together.domain;
import java.sql.Timestamp;
import java.util.ArrayList;

import lombok.Data;

@Data
public class EnterpriseVO {
	//enterprise 테이블에 있는 기본 컬럼들
	private String etp_cd;
	private String user_id;
	private String etp_addr;
	private String etp_ph_no;
	private String etp_license_no;
	private String etp_nm;
	private String etp_content;
	private String etp_email;
	private String etp_apply_dt;
	private String etp_accept_dt;
	private String etp_lat;
	private String etp_lnt;
	
	
	// 업체 정보를 볼때 추가적으로 필요한 변수를 선언 (etpManage 페이지)
	private String user_nm;
	private int authority_no;
	private String etp_cd_substr; // 오라클 substring을 사용하여서 변수 추가
	private String ph_no;
	
	// 업체 상세정보를 볼때 필요한 변수 선언
	
	private String etp_if_cd;
	private String etp_if_info;
	private String etp_if_intro;
	private String etp_if_exter;
	private String etp_if_time1;
	private String etp_if_time2;
	private String etp_if_img_path;
	private String etp_if_hos_time;
	
	
	//업체 상품을볼때 필요한 변수
	private String pd_cd;
	private String pd_nm;
	private int pd_price;
	private String pd_content;
	private String pd_img_path;
	
	
	//주문리스트
	private String or_cd;
	private Timestamp or_dt;
	private Timestamp or_dt2;
	private Timestamp th_dt;
	private String or_stat;
	private String or_check;
	private int or_price;
	private String chardate;
	private String chardate2;
	private String charthisdate;
	private String price;
	
	private String funeral_th_dt; // 장례 업체 주문 목록 : 주문 날짜
	
	//재고
	private String st_cd;
	private int st_total_num;
	private int st_this_num;
	
	
	//형변환	
	private String if_intro;
	private String if_info;
	
	

	//병원 카테고리
	private String etp_ct;
	private int etp_no;
	
	
	//병원 첨부파일
	private String etp_ex_cd;
	private String etp_ex_path;

	// 형변환 2
	private String s_or_dt1;
	private String s_or_dt2;
	
	//count 변수
	private String reviewcount;
	private String reviewavg;
	

}
