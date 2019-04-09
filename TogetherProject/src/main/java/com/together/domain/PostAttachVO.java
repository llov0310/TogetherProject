package com.together.domain;


import lombok.Data;

@Data
public class PostAttachVO {
	private String pa_cd;
	private String pt_cd;
	private int pa_size;
	private String pa_or_path;
	private String pa_path;
}
