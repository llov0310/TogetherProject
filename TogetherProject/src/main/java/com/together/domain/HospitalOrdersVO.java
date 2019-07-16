package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class HospitalOrdersVO {

	
	private String hor_cd;
	private String etp_cd;
	private String user_id;
	private String hor_stat;
	private String hor_check;
	private String hor_pet_cd;
	private String hor_detail;
	
	private Timestamp hor_dt_f;
	private Timestamp hor_dt_l;
	
	//timestamp 형변환 전용
	private String hor_dt_cf;
	private String hor_dt_cl;
	
	//Detail테이블
	private String hod_cd;
	private String hod_canser;
	
	//Member테이블
	private String ph_no;
	private String user_nm;
}
