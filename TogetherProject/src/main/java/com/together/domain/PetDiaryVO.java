package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PetDiaryVO {

	
	private String pet_dia_cd;
	private String user_id;
	private Timestamp pet_dia_settime1;
	private Timestamp pet_dia_settime2;
	private String pet_dia_petcode;
	private String pet_dia_content;
	private String pet_dia_type;
	
	//형변환
	private String pet_dia_settime1_char;
	private String pet_dia_settime2_char;
	
	//조인을 위한 MemberVO
	private String ph_no;
	private String user_nm;
	private String birth_dt;
	private String f_uid; //파이어베이스 아이디가 만들어질 때 생성되는 uid
	
}
