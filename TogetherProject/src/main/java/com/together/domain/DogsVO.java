package com.together.domain;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class DogsVO {
	//dogs 테이블에 있는 기본 컬럼들
	private String d_cd;
	private String user_id;
	private int d_gender;
	private Timestamp d_dt;
	private String d_addr;
	private String d_kind;
	private String d_nm;
	private String d_content;
	private String d_age;
	
	// 반려견 리스트를 볼때 추가적으로 필요한 변수를 선언
	private String user_nm;
	private String ph_no; //검색할때도 필요한 변수
	
	
	
}
