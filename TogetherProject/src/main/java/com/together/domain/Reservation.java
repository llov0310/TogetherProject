package com.together.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Reservation {
	private String rsv_cd;
	private String hpt_cd;
	private String user_id;
	private Timestamp rsv_dt;
}
