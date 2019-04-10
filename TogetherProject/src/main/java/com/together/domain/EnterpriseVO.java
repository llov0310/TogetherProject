package com.together.domain;
import lombok.Data;

@Data
public class EnterpriseVO {
	private String etp_cd;
	private String user_id;
	private String etp_addr;
	private String etp_ph_no;
	private String etp_license_no;
	private String etp_nm;
	private String etp_content;
	private String etp_email;
	
	// 업체 리스트를 보기 위해 추가한 변수들
	private String user_nm;
	private int authority_no;
}
