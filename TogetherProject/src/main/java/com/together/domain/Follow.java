package com.together.domain;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class Follow {
	private String user_id;
	private String user_id2;
	private Timestamp follow_dt;
}
