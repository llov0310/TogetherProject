package com.together.domain;
import lombok.Data;

@Data
public class EnterpriseVO {
	//enterprise 테이블에 있는 기본 컬럼들
	private String etp_cd;
	private String user_id;
	private String etp_addr;
	private String etp_ph_no;
	private String etp_license_no;
	private String etp_nm;
	private String etp_content;
	private String etp_email;
	
	// 업체 리스트를 볼때 추가적으로 필요한 변수를 선언
	private String user_nm;
	private int authority_no;
}
