package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVO {
	
	private String user_id;
	private String password;
	private String ph_no;
	private String user_nm;
	private Timestamp register_dt;
	private int authority_no;
	private String birth_dt;
	private String f_uid; //파이어베이스 아이디가 만들어질 때 생성되는 uid
	
	// 관리자 페이지 : json을 사용하여 라인 차트를 그릴 때 필요한 변수 선언 - 해당 연도 월별 가입자 수 확인
	private String count;
	private String month;
	
	// 관리자 페이지 : 도넛 차트를 그릴 때 필요한 변수 선언
	private String gender_ck;
	private String age_10;
	private String age_20;
	private String age_30;
	private String age_40;
	private String age_50;
	private String age_60_over;
}
