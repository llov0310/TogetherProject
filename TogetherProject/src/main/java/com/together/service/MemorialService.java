package com.together.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.together.domain.DogsVO;
import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.SadBoardVO;

public interface MemorialService {
	
	
	// 첨부파일 삭제
	public void deleteFile(String fullName);
	
	// 첨부파일 목록
	public List<String> getAttach(int bno);
	
	// 첨부파일 저장
	public void addAttach(String fullName);
	
	//글쓰기
	public void write(SadBoardVO vo) throws Exception; 
	
	//글읽기
	public SadBoardVO read(String sb_cd) throws Exception;
	
	//글수정
	public void update(SadBoardVO vo) throws Exception;
	
	//글삭제
	public void delete(String sb_cd) throws Exception;
	
	//목록 (나중에 페이지 나누기)
	public List<SadBoardVO> listAll(int start, int end) throws Exception;
	
	
	
	
}
