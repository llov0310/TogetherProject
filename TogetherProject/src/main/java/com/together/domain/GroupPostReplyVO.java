package com.together.domain;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class GroupPostReplyVO {
	private String gpr_cd;
	private String gp_cd;
	private String user_id;
	private String gpr_content;
	private Timestamp gpr_dt;
}
