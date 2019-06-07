package com.together.domain;

import lombok.Data;

@Data
public class ProductVO {
	private String pd_cd;
	private String etp_cd;
	private String pd_nm;
	private int pd_price;
	private String pd_content;
	private String pd_img_path; //파이어베이스 스토리지 이미지 다운로드 경로
}
