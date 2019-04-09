package com.together.domain;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class GroupMemberVO {
	private String gm_cd;
	private Timestamp gm_dt;
	private String g_cd;
	private String user_id;
}
