package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVO {
	
	private String user_id;
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
