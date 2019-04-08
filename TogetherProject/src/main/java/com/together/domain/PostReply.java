package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PostReply {
	private String pt_reply_cd;
	private String user_id;
	private String pt_cd;
	private String pt_reply_content;
	private Timestamp pt_dt;
	
}
