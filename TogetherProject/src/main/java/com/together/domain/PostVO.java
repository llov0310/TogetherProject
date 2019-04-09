package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PostVO {
	private String pt_cd;
	private String user_id;
	private String pt_content;
	private Timestamp pt_dt;
}
