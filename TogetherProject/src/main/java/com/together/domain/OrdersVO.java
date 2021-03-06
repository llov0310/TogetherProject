package com.together.domain;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class OrdersVO {
	private String or_cd;
	private String user_id;
	private String pd_cd;
	private Timestamp or_dt;
	private Timestamp or_dt2;
	private Timestamp th_dt;
	private String or_stat;
	private String or_check;
	private int or_price;

	//주문리스트 보기위한변수
	private String pd_nm;
	private String etp_cd;
	private String etp_nm;
	private String pd_price;

	//형변환 변수
	private String first_day;
	private String last_day;
	private Date date_day;

	//member의 id변수
	private String user_nm;
	private String ph_no;
	private String cd;
	
	//변환 함수
	private String s_or_dt1;
	private String s_or_dt2;
	private String s_th_dt;	
	
}