package com.together.service;

import java.sql.Timestamp;

import com.together.domain.SadBoardVO;

public interface MemorialService {
	
	public SadBoardVO uploadPost(String sb_cd, String user_id, String sb_title, String sb_content, int sb_sad, Timestamp sb_dt);
	
	
	
}