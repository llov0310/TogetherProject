package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SadBoardReplyVO {
	
	private String sb_reply_cd;
	private String user_id;
	private String sb_cd;
	private String sb_reply_content;
	private Timestamp sb_reply_dt;
}
