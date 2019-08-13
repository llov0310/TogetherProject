package com.together.domain;

import lombok.Data;

@Data
public class UserOrderVO {
	
	private String or_cd;
	private String or_stat;
	private String or_dt;
	private String or_dt2;
	private String th_dt;
	private int or_price;
	private String etp_cd;
	private String etp_nm;

}
