package com.together.domain;

import lombok.Data;

@Data
public class Search { // 페이징처리와 검색 기능을 위한 java
	private int page;
	private String searchType;
	private String keyword;
	private String site_id;
}
