package com.together.service;
import java.util.ArrayList;

import com.together.domain.DogsVO;
import com.together.domain.MemberVO;
public interface MypageService {
	public ArrayList<MemberVO> passCheak(String user_id);

	public Integer passNew(String user_id, String password);

	public Integer infoNew(String user_id, String email, String addr_ji, String addr_dong, String phon);




}
