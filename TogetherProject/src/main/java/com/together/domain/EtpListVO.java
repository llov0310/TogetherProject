package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class EtpListVO { //관리자 페이지 업체 리스트 (enterpriseList) 페이지에 사용 될 VO
	//enterprise 테이블에 있는 기본 컬럼들
	private String etp_cd;
	private String user_id;
	private String etp_addr;
	private String etp_ph_no;
	private String etp_license_no;
	private String etp_nm;
	private String etp_content;
	private String etp_email;
	
	private String etp_cd_substr; // 오라클 substring을 사용하여서 변수 추가
	
	
	//member테이블에 있는 기본 컬럼들
	private String password;
	private String ph_no;
	private String email;
	private String user_nm;
	private Timestamp register_dt;
	private int authority_no;
	private String birth_dt;
	private int sign_no;
	private String addr_ji;
	private String addr_dong;
}
