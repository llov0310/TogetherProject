package com.together.domain;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class Groups {
	private String g_cd;
	private String user_id;
	private String g_nm;
	private String g_place;
	private int g_mem_total;
	private Timestamp g_dt;
	private String g_content;
}
