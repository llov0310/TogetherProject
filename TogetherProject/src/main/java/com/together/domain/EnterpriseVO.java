package com.together.domain;
import java.sql.Timestamp;

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
	
	// 업체 정보를 볼때 추가적으로 필요한 변수를 선언 (etpManage 페이지)
	private String user_nm;
	private int authority_no;
	private String etp_cd_substr; // 오라클 substring을 사용하여서 변수 추가
	private String ph_no;
	
	// 업체 상세정보를 볼때 필요한 변수 선언
	
	private String etp_if_cd;
	private String etp_if_info;
	private String etp_if_intro;
	private String etp_if_exter;
	private String etp_if_time1;
	private String etp_if_time2;
	
	
	
	//업체 상품을볼때 필요한 변수
	
	private String pd_cd;
	
	private String pd_nm;
	private int pd_price;
	private String pd_content;
	private String pd_img_path;
	
}
