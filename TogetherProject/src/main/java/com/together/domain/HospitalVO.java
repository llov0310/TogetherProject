package com.together.domain;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class HospitalVO {
	private String hpt_cd;
	private String hpt_addr;
	private String hpt_ph_no;
	private String hpt_license_no;
	private String hpt_nm;
	private Timestamp hpt_rsv_dt;
}
