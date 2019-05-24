package com.together.mapper;

import org.apache.ibatis.annotations.Param;
import com.together.domain.BoardAttachVO;
import com.together.domain.BoardAttachVO;

public interface BoardAttachMapper {
	//파일 업로드 관련 Mapper
	public int insert(@Param("uid") String uid, 
			@Param("path") String path, 
			@Param("fileName") String uploadFileName);
}
