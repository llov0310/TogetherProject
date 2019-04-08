package com.together.domain;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class Chatting {
	private int chat_cd;
	private String user_id;
	private String cr_cd;
	private String chat_content;
	private Timestamp chat_dt;
}
