package com.together.domain;

import lombok.Data;
@Data
public class BoardAttachVO {
	
	//db에 넣는 데이터
	private String uuid;  //UUID가 포함된 이름 - PK
	private String uploadPath; // 실제파일이업로드된  경로
	private String fileName; // 파일이름



//	private boolean fileType;  //이미지 파일 여부 판단    (일단 막아)
//	
//	private Long bno; // 게시물 번호
}
