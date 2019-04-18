package com.together.service;
import java.util.ArrayList;

import com.together.domain.MemberVO;
public interface MypageService {
	public ArrayList<MemberVO> passCheak(String user_id);	
}
