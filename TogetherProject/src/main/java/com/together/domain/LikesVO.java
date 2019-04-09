package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LikesVO {
	private int like_cd;
	private Timestamp like_dt;
	private String user_id;
	private String pt_cd;
}
