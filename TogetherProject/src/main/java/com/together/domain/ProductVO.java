package com.together.domain;

import lombok.Data;

@Data
public class ProductVO {
	private String pd_cd;
	private String etp_cd;
	private String pd_nm;
	private int pd_price;
	private String pd_content;
	private String pd_img_uuid;
}
