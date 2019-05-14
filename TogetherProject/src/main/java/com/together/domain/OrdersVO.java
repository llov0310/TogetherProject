package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class OrdersVO {
	private String or_cd;
	private String user_id;
	private String pd_cd;
	private Timestamp or_dt;
	
	
	//주문리스트 보기위한변수
	private String pd_nm;
	private String etp_nm;
	private String pd_price;
}	
