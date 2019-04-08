package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Orders {
	private String or_cd;
	private String user_id;
	private String pd_cd;
	private Timestamp or_dt;
}	
