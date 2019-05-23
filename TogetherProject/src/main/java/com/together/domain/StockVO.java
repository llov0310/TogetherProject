package com.together.domain;

import lombok.Data;

@Data
public class StockVO {
	private String st_cd;
	private String pd_cd;
	private int st_total_num;
	private int st_this_num;
}
