package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PetBoardReply {
	private String pbr_cd;
	private String pb_cd;
	private String user_id;
	private String pbr_content;
	private Timestamp pbr_dt;
}
