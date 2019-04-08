package com.together.domain;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class Dogs {
	private String d_cd;
	private String user_id;
	private int d_gender;
	private Timestamp d_dt;
	private String d_addr;
	private String d_kind;
	private String d_nm;
	private String d_content;
}
