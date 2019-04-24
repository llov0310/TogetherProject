package com.together.service;

import java.sql.Timestamp;
import java.util.List;

import com.together.domain.SadBoardVO;
import com.together.mapper.CustomerMapper;
import com.together.mapper.MemorialMapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MemorialServiceImplement implements MemorialService {
	
	private MemorialMapper mapper;
	

	@Override
	public void deleteFile(String fullName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getAttach(int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAttach(String fullName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(SadBoardVO vo) throws Exception {
		
		
	}

	@Override
	public SadBoardVO read(String sb_cd) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SadBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String sb_cd) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SadBoardVO> listAll(int start, int end) throws Exception {
		return mapper.listAll(start, end);
		
	}

 }
