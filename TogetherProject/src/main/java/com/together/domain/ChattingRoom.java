package com.together.domain;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class ChattingRoom {
	private String cr_cd;
	private String user_id;
	private Timestamp cr_dt;
	private String cr_user;
}
