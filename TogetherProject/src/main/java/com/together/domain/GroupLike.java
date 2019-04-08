package com.together.domain;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class GroupLike {
	private int gl_cd;
	private Timestamp gl_dt;
	private String user_id;
	private String gp_cd;
}
