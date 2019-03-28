package com.example.hellomx.domain;

import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

@Data
public class CustomerVO {
	private String cid;
	private String name;
	private Date birthday;
	private String password;
	private String telephone;
	private String email;
	private String address;
	private Timestamp timestamp;
}