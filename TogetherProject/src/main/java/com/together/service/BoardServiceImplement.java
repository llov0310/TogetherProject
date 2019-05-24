package com.together.service;

import org.springframework.stereotype.Service;
import com.together.domain.BoardAttachVO;
import com.together.domain.AttachFileDTO;
import com.together.mapper.BoardAttachMapper;
import com.together.service.BoardService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardServiceImplement implements BoardService{

	private BoardAttachMapper mapper;
	
	@Override
	public int insert(String uid, String path, String uploadFileName) {
//		System.out.println("왜안오는데 ㅁㅊ");
//		System.out.println(uid + path + uploadFileName);
		return mapper.insert(uid,path,uploadFileName);
	}

	
}