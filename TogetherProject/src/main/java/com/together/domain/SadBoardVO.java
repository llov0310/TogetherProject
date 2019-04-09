package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SadBoardVO {
	private String sb_cd;
	private String user_id;
	private String sb_title;
	private String sb_content;
	private int sb_sad;
	private Timestamp sb_dt;
}
