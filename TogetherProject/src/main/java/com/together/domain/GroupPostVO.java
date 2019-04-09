package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class GroupPostVO {
	private String gp_cd;
	private String g_cd;
	private String user_id;
	private String gp_content;
	private Timestamp gp_dt;
	private String gp_category;
}
