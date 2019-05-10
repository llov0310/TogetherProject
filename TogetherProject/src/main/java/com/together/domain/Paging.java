package com.together.domain;

import lombok.Data;

@Data
public class Paging {
	private int totalNum = 0; //
	private int onePageBoard = 10; //OnePageBorad => 한 페이지에 보여줄 멤버(글) 수
	private int currentPageNum;
	
	private int startNum = 0;
	private int endNum = 0;
}
