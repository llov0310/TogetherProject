package com.together.service;
import java.util.ArrayList;

import com.together.domain.DogsVO;
import com.together.domain.MemberVO;
public interface MypageService {
	public ArrayList<MemberVO> passCheak(String user_id);
	
	public ArrayList<MemberVO> memberinfo(String user_id);

	public Integer passNew(String user_id, String password);

	public Integer infoNew(String user_id, String email, String addr_ji, String addr_dong, String phon);

	public ArrayList<DogsVO> petlist(String user_id);

	public Integer addDog(String user_id, String d_nm, int d_gender, String d_kind, String d_content, String d_age);


;

	




}
