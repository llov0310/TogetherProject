package com.together.domain;

import lombok.Data;

@Data
public class MemberVO {
	
	private String userid;
	private String password;
	private String email;
	private String adress;
	private String name;
	private String authority;
}
