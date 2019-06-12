package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReviewBoardVO {
	private String rb_cd;
	private String user_id;
	private String etp_cd;
	private String rb_nm;
	private String rb_contents;
	private int rb_like;
	private Timestamp rb_dt;
	private int rb_avg;
	
	//변환용
	private String rb_dt_char;
}
